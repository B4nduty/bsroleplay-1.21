package banduty.bsroleplay.block.custom;

import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Clockpunch extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = DirectionProperty.of("facing",
        Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST);
    public static final DirectionProperty FACING_UP = DirectionProperty.of("facing_up", Direction.UP, Direction.DOWN);
    public static final MapCodec<Clockpunch> CODEC = createCodec(Clockpunch::new);
    protected static final VoxelShape GROUND_SHAPE = VoxelShapes.union(
            createCuboidShape(1.0, 0.0, 1.0, 15.0, 10.0, 15.0)
    );

    protected static final VoxelShape WALL_SHAPE = VoxelShapes.union(
            createCuboidShape(1.0, 1.0, 5.0, 15.0, 15.0, 16.0)
    );

    public Clockpunch(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING_UP, Direction.DOWN));
    }

    @Override
    protected MapCodec<Clockpunch> getCodec() {
        return CODEC;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction facing = state.get(FACING);

        if (facing.getAxis().isVertical()) {
            return GROUND_SHAPE;
        } else {
            return WALL_SHAPE;
        }
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
        builder.add(FACING_UP);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction clickedFace = ctx.getSide();
        Direction playerHorizontalFacing = ctx.getHorizontalPlayerFacing();

        if (clickedFace == Direction.UP) {
            this.setDefaultState(this.stateManager.getDefaultState().with(FACING_UP, Direction.UP));
        }

        return this.getDefaultState().with(FACING, playerHorizontalFacing.getOpposite());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ClockPunchBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ClockPunchBlockEntity) {
                ItemScatterer.spawn(world, pos, (ClockPunchBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ClockPunchBlockEntity clockPunchBlockEntity && placer != null) {
            clockPunchBlockEntity.setOwner(placer.getUuid());
            clockPunchBlockEntity.markDirty();
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        NamedScreenHandlerFactory clockPunchEntityScreen = (ClockPunchBlockEntity) world.getBlockEntity(pos);
        if (world.isClient) return ActionResult.SUCCESS;
        if (!(world.getBlockEntity(pos) instanceof ClockPunchBlockEntity clockPunchBlockEntity)) return ActionResult.PASS;
        UUID owner = clockPunchBlockEntity.getOwner();
        if (owner.equals(player.getUuid())) {
            player.openHandledScreen(clockPunchEntityScreen);
            return ActionResult.SUCCESS;
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof ClockPunchBlockEntity clockPunchBlockEntity &&
                clockPunchBlockEntity.getOwner().equals(player.getUuid())) return super.calcBlockBreakingDelta(state, player, world, pos);
        else return -1.0f;
    }
}