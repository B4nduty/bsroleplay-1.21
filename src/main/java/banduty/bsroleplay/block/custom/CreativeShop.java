package banduty.bsroleplay.block.custom;

import banduty.bsroleplay.block.entity.shops.CreativeShopBlockEntity;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.item.WalletItem;
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
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CreativeShop extends BlockWithEntity implements BlockEntityProvider {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    public static final MapCodec<CreativeShop> CODEC = createCodec(CreativeShop::new);
    protected static final VoxelShape WOOD = createCuboidShape(1.0, 0.0, 1.0, 15.0, 10.0, 15.0);
    protected static final VoxelShape GLASS = createCuboidShape(4.0, 10.0, 4.0, 12.0, 16.0, 12.0);
    protected static final VoxelShape SHAPE = VoxelShapes.union(WOOD, GLASS);

    public CreativeShop(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<CreativeShop> getCodec() {
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
        return new CreativeShopBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof CreativeShopBlockEntity) {
                ItemScatterer.spawn(world, pos, (CreativeShopBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof CreativeShopBlockEntity creativeShopBlockEntity && placer != null) {
            creativeShopBlockEntity.setOwner(placer.getUuid());
            creativeShopBlockEntity.markDirty();
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        NamedScreenHandlerFactory creativeShopBlockEntityScreen = (CreativeShopBlockEntity) world.getBlockEntity(pos);
        if (world.isClient) return ActionResult.SUCCESS;
        if (!(world.getBlockEntity(pos) instanceof CreativeShopBlockEntity creativeShopBlockEntity)) return ActionResult.PASS;
        if (creativeShopBlockEntity.getOwner().equals(player.getUuid()) && player.isCreative()) {
            player.openHandledScreen(creativeShopBlockEntityScreen);
            return ActionResult.SUCCESS;
        }
        ItemStack mainHandStack = player.getMainHandStack();
        ItemStack sellStack = creativeShopBlockEntity.getRenderStack();
        if (sellStack.isEmpty()) {
            player.sendMessage(Text.translatable("bsroleplay.shop.no_item_sell").formatted(Formatting.RED), true);
            return ActionResult.PASS;
        }
        if (mainHandStack.getItem() != ModItems.WALLET) {
            player.sendMessage(Text.translatable("bsroleplay.shop.wallet_need").formatted(Formatting.RED), true);
            return ActionResult.PASS;
        }
        if (WalletItem.getCurrencyFromNbt(mainHandStack) < creativeShopBlockEntity.getCurrencyCounter()) {
            player.sendMessage(Text.translatable("bsroleplay.shop.no_money").formatted(Formatting.RED), true);
            return ActionResult.PASS;
        }
        WalletItem.writeCurrencyToNbt(mainHandStack,
                WalletItem.getCurrencyFromNbt(mainHandStack) - creativeShopBlockEntity.getCurrencyCounter());
        player.getInventory().insertStack(sellStack.copyWithCount(1));
        return ActionResult.SUCCESS;
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof CreativeShopBlockEntity creativeShopBlockEntity &&
                creativeShopBlockEntity.getOwner().equals(player.getUuid()) &&
                player.isCreative()) return super.calcBlockBreakingDelta(state, player, world, pos);
        else return -1.0f;
    }
}