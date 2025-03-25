package banduty.bsroleplay.screen.clockpunch;

import banduty.bsroleplay.block.entity.ClockPunchBlockEntity;
import banduty.bsroleplay.item.custom.blocks.currency.CoinItem;
import banduty.bsroleplay.networking.packet.UpdateCurrencyPacketS2CPacket;
import banduty.bsroleplay.screen.ModScreenHandlers;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

public class ClockPunchScreenHandler extends ScreenHandler {
    private final Inventory coinsInventory;
    private final PropertyDelegate propertyDelegateLower;
    private final PropertyDelegate propertyDelegateUpper;
    public final ClockPunchBlockEntity blockEntity;

    public ClockPunchScreenHandler(int syncId, PlayerInventory playerInventory, ClockPunchBlockEntity.Data data) {
        this(syncId, playerInventory, data, new ArrayPropertyDelegate(2), new ArrayPropertyDelegate(2));
    }

    public ClockPunchScreenHandler(int syncId, PlayerInventory playerInventory, ClockPunchBlockEntity.Data data, PropertyDelegate propertyDelegateLower, PropertyDelegate propertyDelegateUpper) {
        super(ModScreenHandlers.CLOCKPUNCH_SCREEN_HANDLER, syncId);
        BlockEntity blockEntity = playerInventory.player.getWorld().getBlockEntity(data.blockPos());
        this.blockEntity = ((ClockPunchBlockEntity) blockEntity);
        this.coinsInventory = ((Inventory) blockEntity);
        if (coinsInventory != null) coinsInventory.onOpen(playerInventory.player);
        this.propertyDelegateLower = propertyDelegateLower;
        this.propertyDelegateUpper = propertyDelegateUpper;

        addCoinsInventory();
        addPlayerHotbar(playerInventory);

        addProperties(this.propertyDelegateLower);
        addProperties(this.propertyDelegateUpper);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return super.onButtonClick(player, id);
    }

    public void setSalaryCounter(int value) {
        if (value >= 0) {
            this.blockEntity.setSalaryCounter(value);
            this.propertyDelegateLower.set(0, value & 0xFFFF);
            this.propertyDelegateUpper.set(0, (value >> 16) & 0xFFFF);
        }
    }

    public int getSalaryCounter() {
        int lower = this.propertyDelegateLower.get(0);
        int upper = this.propertyDelegateUpper.get(0);
        return (upper << 16) | (lower & 0xFFFF);
    }

    private void sendCurrencyUpdatePacket(ServerPlayerEntity player, int amount) {
        ServerPlayNetworking.send(player, new UpdateCurrencyPacketS2CPacket(this.syncId, amount));
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack originalStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);
        if (slot.hasStack()) {
            ItemStack newStack = slot.getStack();
            originalStack = newStack.copy();

            int shopStart = 0; // First slot of the shop inventory
            int shopEnd = 27;  // Last slot of the shop inventory (28 slots: 0-27)
            int playerStart = 28; // First slot of the player inventory
            int playerEnd = 37;

            // Move items from the shop to the player inventory
            if (slotIndex >= shopStart && slotIndex <= shopEnd) {
                if (!this.insertItem(newStack, playerStart, playerEnd, false)) {
                    return ItemStack.EMPTY;
                }

                // Update coinCounter when coins are removed from the shop
                if (newStack.getItem() instanceof CoinItem coinItem) {
                    int coinsRemoved = originalStack.getCount() - newStack.getCount();
                    if (coinsRemoved > 0) {
                        int newCoinsCounter = getCoinsCounter() - coinItem.currencyValue * coinsRemoved;
                        setCoinsCounter(newCoinsCounter);
                    }
                }
            }
            // Move items from the player inventory to the shop
            else if (slotIndex >= playerStart && slotIndex < playerEnd) {
                if (!this.insertItem(newStack, shopStart, shopEnd, false)) {
                    return ItemStack.EMPTY;
                }

                // Update coinCounter when coins are added to the shop
                if (newStack.getItem() instanceof CoinItem coinItem) {
                    int coinsAdded = originalStack.getCount() - newStack.getCount();
                    if (coinsAdded > 0) {
                        int newCoinsCounter = getCoinsCounter() + coinItem.currencyValue * coinsAdded;
                        setCoinsCounter(newCoinsCounter);
                    }
                }
            }

            if (newStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (newStack.getCount() == originalStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, newStack);
        }

        return originalStack;
    }

    private void addCoinsInventory() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 7; x++) {
                this.addSlot(new CoinSlot(this.coinsInventory, x + y * 7, 170 + 18 * y, 30 + 18 * x));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 44 + i * 18, 168));
        }
    }

    public void setCoinsCounter(int value) {
        this.blockEntity.setCoinsCounter(value);
        this.propertyDelegateLower.set(1, value & 0xFFFF);
        this.propertyDelegateUpper.set(1, (value >> 16) & 0xFFFF);
    }

    public int getCoinsCounter() {
        int lower = this.propertyDelegateLower.get(1);
        int upper = this.propertyDelegateUpper.get(1);
        return (upper << 16) | (lower & 0xFFFF);
    }

    private class CoinSlot extends Slot {
        public CoinSlot(Inventory inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return stack.getItem() instanceof CoinItem coinItem && coinItem.currencyValue > 0;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack takenStack) {
            super.onTakeItem(player, takenStack);
            if (takenStack.getItem() instanceof CoinItem coinItem) {
                int coinsRemoved = takenStack.getCount();
                int newCoinsCounter = getCoinsCounter() - coinItem.currencyValue * coinsRemoved;
                setCoinsCounter(newCoinsCounter);

                if (player instanceof ServerPlayerEntity serverPlayer) {
                    sendCurrencyUpdatePacket(serverPlayer, newCoinsCounter);
                }
            }
        }

        @Override
        public void onQuickTransfer(ItemStack newItem, ItemStack original) {
            super.onQuickTransfer(newItem, original);
            if (original.getItem() instanceof CoinItem coinItem) {
                int coinsTransferred = original.getCount() - newItem.getCount();

                int newCoinsCounter = getCoinsCounter() + coinItem.currencyValue * coinsTransferred;
                setCoinsCounter(newCoinsCounter);
            }
        }

        @Override
        public void setStack(ItemStack stack) {
            ItemStack originalStack = this.getStack();
            super.setStack(stack);

            if (stack.getItem() instanceof CoinItem coinItem) {
                int coinsInserted = stack.getCount() - (originalStack.isEmpty() ? 0 : originalStack.getCount());
                if (coinsInserted != 0) {
                    int newCoinsCounter = getCoinsCounter() + coinItem.currencyValue * coinsInserted;
                    setCoinsCounter(newCoinsCounter);
                }
            }
        }

        @Override
        public boolean disablesDynamicDisplay() {
            return true;
        }
    }
}