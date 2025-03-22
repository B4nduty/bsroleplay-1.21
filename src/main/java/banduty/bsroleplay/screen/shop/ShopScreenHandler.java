
package banduty.bsroleplay.screen.shop;

import banduty.bsroleplay.block.entity.shops.ShopBlockEntity;
import banduty.bsroleplay.item.ModItems;
import banduty.bsroleplay.item.custom.blocks.currency.CoinItem;
import banduty.bsroleplay.networking.packet.UpdateCurrencyPacketS2CPacket;
import banduty.bsroleplay.screen.ModScreenHandlers;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

public class ShopScreenHandler extends ScreenHandler {
    private final Inventory sellInventory;
    private final Inventory coinsInventory = new SimpleInventory(4);
    private final PropertyDelegate propertyDelegate;
    public final ShopBlockEntity blockEntity;

    public ShopScreenHandler(int syncId, PlayerInventory playerInventory, ShopBlockEntity.Data data) {
        this(syncId, playerInventory, data, new ArrayPropertyDelegate(2));
    }

    public ShopScreenHandler(int syncId, PlayerInventory playerInventory, ShopBlockEntity.Data data, PropertyDelegate arrayPropertyDelegate) {
        super(ModScreenHandlers.SHOP_SCREEN_HANDLER, syncId);
        BlockEntity blockEntity = playerInventory.player.getWorld().getBlockEntity(data.blockPos());
        this.blockEntity = ((ShopBlockEntity) blockEntity);
        this.sellInventory = ((Inventory) blockEntity);
        if (sellInventory != null) sellInventory.onOpen(playerInventory.player);
        this.propertyDelegate = arrayPropertyDelegate;

        this.addSlot(new Slot(this.sellInventory, 0, 104, 11));
        this.addSlot(new Slot(this.sellInventory, 1, 182, 27));
        this.addSlot(new Slot(this.sellInventory, 2, 207, 27));
        this.addSlot(new Slot(this.sellInventory, 3, 182, 52));
        this.addSlot(new Slot(this.sellInventory, 4, 207, 52));
        this.addSlot(new Slot(this.sellInventory, 5, 182, 77));
        this.addSlot(new Slot(this.sellInventory, 6, 207, 77));

        this.addSlot(new CoinOutputSlot(this.coinsInventory, 0, 9, 61, (CoinItem) ModItems.COPPER_COIN));
        this.addSlot(new CoinOutputSlot(this.coinsInventory, 1, 36, 61, (CoinItem) ModItems.GOLD_COIN));
        this.addSlot(new CoinOutputSlot(this.coinsInventory, 2, 124, 61, (CoinItem) ModItems.AMETHYST_COIN));
        this.addSlot(new CoinOutputSlot(this.coinsInventory, 3, 152, 61, (CoinItem) ModItems.NETHERITE_COIN));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        onCoinsAdded(this.propertyDelegate.get(1));

        addProperties(this.propertyDelegate);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return super.onButtonClick(player, id);
    }

    private void onCoinsAdded(int storedCoins) {
        for (Slot slot : this.slots) {
            if (slot instanceof CoinOutputSlot coinOutputSlot) {
                coinOutputSlot.setStack(new ItemStack(coinOutputSlot.coinItem, storedCoins /
                        coinOutputSlot.coinItem.currencyValue));
            }
        }
    }

    public void setCurrencyCounter(int value) {
        if (value >= 0 && value <= 32000) {
            this.blockEntity.setCurrencyCounter(value);
            this.propertyDelegate.set(0, value);
        }
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
            int shopEnd = 6;   // Last slot of the shop inventory (7 slots: 0-6)
            int playerStart = 7; // First slot of the player inventory
            int playerEnd = 43;  // Last slot of the player inventory (36 slots: 7-42)

            // Move items from the shop to the player inventory
            if (slotIndex >= shopStart && slotIndex <= shopEnd) {
                if (!this.insertItem(newStack, playerStart, playerEnd, false)) {
                    return ItemStack.EMPTY;
                }
            }
            // Move items from the player inventory to the shop
            else if (slotIndex >= playerStart && slotIndex < playerEnd) {
                if (!this.insertItem(newStack, shopStart, shopEnd, false)) {
                    return ItemStack.EMPTY;
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

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public class CoinOutputSlot extends Slot {
        private final CoinItem coinItem;

        public CoinOutputSlot(Inventory inventory, int index, int x, int y, CoinItem coinItem) {
            super(inventory, index, x, y);
            this.coinItem = coinItem;
        }

        @Override
        public boolean canInsert(ItemStack stack) {
            return false;
        }

        @Override
        public void onTakeItem(PlayerEntity player, ItemStack takenStack) {
            super.onTakeItem(player, takenStack);
            if (player instanceof ServerPlayerEntity serverPlayerEntity) sendCurrencyUpdatePacket(
                    serverPlayerEntity, ShopScreenHandler.this.propertyDelegate.get(1) -
                            this.coinItem.currencyValue * takenStack.getCount());
        }

        @Override
        public void onQuickTransfer(ItemStack newItem, ItemStack original) {
            super.onQuickTransfer(newItem, original);
            ShopScreenHandler.this.propertyDelegate.set(1, ShopScreenHandler.this.propertyDelegate.get(1) -
                    this.coinItem.currencyValue * (original.getCount() - newItem.getCount()));
        }

        @Override
        public boolean disablesDynamicDisplay() {
            return true;
        }
    }
}
