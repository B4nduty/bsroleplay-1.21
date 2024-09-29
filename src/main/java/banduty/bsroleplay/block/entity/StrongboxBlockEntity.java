
package banduty.bsroleplay.block.entity;
import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.shops.ImplementedInventory;
import banduty.bsroleplay.screen.strongbox.StrongboxScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.RenderUtil;

import java.util.UUID;

public class StrongboxBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<StrongboxBlockEntity.Data>, ImplementedInventory, GeoBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected UUID owner = Util.NIL_UUID;
    protected boolean open;

    public StrongboxBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STRONGBOX_BLOCK_ENTITY, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", 0, this::predicate));
    }

    @Override
    public void onOpen(PlayerEntity player) {
        open = true;
    }

    private PlayState predicate(AnimationState<StrongboxBlockEntity> animationState) {
        if (open) animationState.getController().setAnimation(RawAnimation.begin().then("open", Animation.LoopType.HOLD_ON_LAST_FRAME));
        else animationState.getController().setAnimation(RawAnimation.begin().then("close", Animation.LoopType.HOLD_ON_LAST_FRAME));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtil.getCurrentTick();
    }

    @Override
    public Data getScreenOpeningData(ServerPlayerEntity player) {
        return createData();
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Shop");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putUuid("shop.owner", owner);
        nbt.putBoolean("strongbox.open", open);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        owner = nbt.contains("shop.owner") ? nbt.getUuid("shop.owner") : Util.NIL_UUID;
        open = nbt.getBoolean("strongbox.open");
    }

    public Data createData() {
        return new Data(this.getPos());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new StrongboxScreenHandler(syncId, playerInventory, this.createData());
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        markDirty();
    }

    public void setOpen(boolean open) {
        this.open = open;
        markDirty();
    }

    public UUID getOwner() {
        return owner;
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound nbt = new NbtCompound();
        this.writeNbt(nbt, registryLookup);
        return nbt;
    }

    public record Data(BlockPos blockPos) implements CustomPayload {

        public static final Id<StrongboxBlockEntity.Data> IDENTIFIER = new Id<>(BsRolePlay.identifierOf("strongbox_block_entity"));

        @Override
        public Id<? extends CustomPayload> getId() {
            return IDENTIFIER;
        }

        public static final PacketCodec<RegistryByteBuf, StrongboxBlockEntity.Data> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, StrongboxBlockEntity.Data::blockPos,
                StrongboxBlockEntity.Data::new
        );
    }
}
