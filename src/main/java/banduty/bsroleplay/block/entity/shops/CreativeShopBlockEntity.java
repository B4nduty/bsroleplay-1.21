
package banduty.bsroleplay.block.entity.shops;
import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.block.entity.ModBlockEntities;
import banduty.bsroleplay.screen.creative_shop.CreativeShopScreenHandler;
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
import net.minecraft.screen.PropertyDelegate;
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

public class CreativeShopBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<CreativeShopBlockEntity.Data>, ImplementedInventory, GeoBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected UUID owner = Util.NIL_UUID;
    private static final int SELL_SLOT = 0;

    protected final PropertyDelegate propertyDelegate;
    private int currencyCounter = 0;

    public CreativeShopBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CREATIVE_SHOP_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return CreativeShopBlockEntity.this.currencyCounter;
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    CreativeShopBlockEntity.this.currencyCounter = value;
                }
            }

            @Override
            public int size() {
                return 1;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this,"controller", 0, this::predicate));
    }

    private PlayState predicate(AnimationState<CreativeShopBlockEntity> animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.STOP;
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
        return Text.literal("Creative Shop");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("creative_shop.currency_counter", currencyCounter);
        nbt.putUuid("creative_shop.owner", owner);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        currencyCounter = nbt.getInt("creative_shop.currency_counter");
        owner = nbt.contains("creative_shop.owner") ? nbt.getUuid("creative_shop.owner") : Util.NIL_UUID;
    }

    public CreativeShopBlockEntity.Data createData() {
        return new CreativeShopBlockEntity.Data(this.getPos());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CreativeShopScreenHandler(syncId, playerInventory, this.createData(), this.propertyDelegate);
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        markDirty();
    }

    public UUID getOwner() {
        return owner;
    }

    public void setCurrencyCounter(int currencyCounter) {
        this.currencyCounter = currencyCounter;
        markDirty();
    }

    public int getCurrencyCounter() {
        return currencyCounter;
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

    public ItemStack getRenderStack() {
        return this.getStack(SELL_SLOT);
    }

    public record Data(BlockPos blockPos) implements CustomPayload {

        public static final CustomPayload.Id<CreativeShopBlockEntity.Data> IDENTIFIER = new CustomPayload.Id<>(BsRolePlay.identifierOf("creative_shop_block_entity"));

        @Override
        public Id<? extends CustomPayload> getId() {
            return IDENTIFIER;
        }

        public static final PacketCodec<RegistryByteBuf, CreativeShopBlockEntity.Data> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, CreativeShopBlockEntity.Data::blockPos,
                CreativeShopBlockEntity.Data::new
        );
    }
}
