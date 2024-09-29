package banduty.bsroleplay.block.custom;

import banduty.bsroleplay.block.entity.StrongboxBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Strongbox extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<Strongbox> CODEC = createCodec(Strongbox::new);
    protected static final VoxelShape BOX = createCuboidShape(2.0, 2.0, 2.0, 14.0, 16.0, 14.0);
    protected static final VoxelShape FEET_1 = createCuboidShape(2.0, 0.0, 2.0, 3.0, 2.0, 3.0);
    protected static final VoxelShape FEET_2 = createCuboidShape(13.0, 0.0, 2.0, 14.0, 2.0, 3.0);
    protected static final VoxelShape FEET_3 = createCuboidShape(2.0, 0.0, 13.0, 3.0, 2.0, 14.0);
    protected static final VoxelShape FEET_4 = createCuboidShape(13.0, 0.0, 13.0, 14.0, 2.0, 14.0);
    protected static final VoxelShape SHAPE = VoxelShapes.union(BOX, FEET_1, FEET_2, FEET_3, FEET_4);

    public Strongbox(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<Strongbox> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState()
                .with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StrongboxBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof StrongboxBlockEntity) {
                ItemScatterer.spawn(world, pos, (StrongboxBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof StrongboxBlockEntity strongboxBlockEntity && placer != null) {
            strongboxBlockEntity.setOwner(placer.getUuid());
            strongboxBlockEntity.markDirty();
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        NamedScreenHandlerFactory StrongboxBlockEntityScreen = (StrongboxBlockEntity) world.getBlockEntity(pos);
        if (world.isClient) return ActionResult.SUCCESS;
        if (!(world.getBlockEntity(pos) instanceof StrongboxBlockEntity strongboxBlockEntity)) return ActionResult.PASS;
        UUID owner = strongboxBlockEntity.getOwner();
        if (owner.equals(player.getUuid())) {
            player.openHandledScreen(StrongboxBlockEntityScreen);
            return ActionResult.SUCCESS;
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof StrongboxBlockEntity StrongboxBlockEntity &&
                StrongboxBlockEntity.getOwner().equals(player.getUuid())) return super.calcBlockBreakingDelta(state, player, world, pos);
        else return -1.0f;
    }

    @Override
    public float getBlastResistance() {
        return 1200.0F;
    }
}