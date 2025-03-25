
package banduty.bsroleplay.block.entity;

import banduty.bsroleplay.BsRolePlay;
import banduty.bsroleplay.screen.clockpunch.ClockPunchScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.RenderUtil;

import java.util.UUID;

public class ClockPunchBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory<ClockPunchBlockEntity.Data>, ImplementedInventory, GeoBlockEntity {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(28, ItemStack.EMPTY);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    protected UUID owner = Util.NIL_UUID;

    protected final PropertyDelegate propertyDelegateLower;
    protected final PropertyDelegate propertyDelegateUpper;
    public int salaryCounter = 0;
    public int coinsCounter = 0;

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (world != null && world.isClient) {
            world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
        return true;
    }

    public ClockPunchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CLOCKPUNCH_BLOCK_ENTITY, pos, state);
        this.propertyDelegateLower = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ClockPunchBlockEntity.this.salaryCounter & 0xFFFF; // Lower 16 bits
                    case 1 -> ClockPunchBlockEntity.this.coinsCounter & 0xFFFF; // Lower 16 bits
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ClockPunchBlockEntity.this.salaryCounter = (ClockPunchBlockEntity.this.salaryCounter & 0xFFFF0000) | (value & 0xFFFF);
                    case 1 -> ClockPunchBlockEntity.this.coinsCounter = (ClockPunchBlockEntity.this.coinsCounter & 0xFFFF0000) | (value & 0xFFFF);
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };

        this.propertyDelegateUpper = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (ClockPunchBlockEntity.this.salaryCounter >> 16) & 0xFFFF; // Upper 16 bits
                    case 1 -> (ClockPunchBlockEntity.this.coinsCounter >> 16) & 0xFFFF; // Upper 16 bits
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ClockPunchBlockEntity.this.salaryCounter = (ClockPunchBlockEntity.this.salaryCounter & 0xFFFF) | ((value & 0xFFFF) << 16);
                    case 1 -> ClockPunchBlockEntity.this.coinsCounter = (ClockPunchBlockEntity.this.coinsCounter & 0xFFFF) | ((value & 0xFFFF) << 16);
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

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
    public ClockPunchBlockEntity.Data getScreenOpeningData(ServerPlayerEntity player) {
        return createData();
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("ClockPunch");
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
        nbt.putInt("clockpunch.currency_counter", salaryCounter);
        nbt.putInt("clockpunch.coins_counter", coinsCounter);
        nbt.putUuid("clockpunch.owner", owner);
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
        salaryCounter = nbt.getInt("clockpunch.currency_counter");
        coinsCounter = nbt.getInt("clockpunch.coins_counter");
        owner = nbt.contains("clockpunch.owner") ? nbt.getUuid("clockpunch.owner") : Util.NIL_UUID;
    }

    public ClockPunchBlockEntity.Data createData() {
        return new ClockPunchBlockEntity.Data(this.getPos());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ClockPunchScreenHandler(syncId, playerInventory, this.createData(), this.propertyDelegateLower, this.propertyDelegateUpper);
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
        markDirty();
    }

    public UUID getOwner() {
        return owner;
    }

    public void setSalaryCounter(int salaryCounter) {
        this.salaryCounter = salaryCounter;
        markDirty();
    }

    public void setCoinsCounter(int coinsCounter) {
        this.coinsCounter = coinsCounter;
        markDirty();
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

    public void reduceSellStack(ItemStack itemStack, int decrease) {
        for (int i = 0; i < 7; i++) {
            if (inventory.get(i).getItem() == itemStack.getItem()) {
                this.removeStack(i, decrease);
            }
        }
        markDirty();
    }

    public ItemStack getSellStack() {
        for (int i = 0; i < 7; i++) {
            if (inventory.get(i).getItem() != null && inventory.get(i).getItem() != Items.AIR) {
                return this.getStack(i);
            }
        }
        return ItemStack.EMPTY;
    }

    public record Data(BlockPos blockPos) implements CustomPayload {

        public static final CustomPayload.Id<ClockPunchBlockEntity.Data> IDENTIFIER = new CustomPayload.Id<>(BsRolePlay.identifierOf("clockpunch_block_entity"));

        @Override
        public Id<? extends CustomPayload> getId() {
            return IDENTIFIER;
        }

        public static final PacketCodec<RegistryByteBuf, ClockPunchBlockEntity.Data> CODEC = PacketCodec.tuple(
                BlockPos.PACKET_CODEC, ClockPunchBlockEntity.Data::blockPos,
                ClockPunchBlockEntity.Data::new
        );
    }
}
