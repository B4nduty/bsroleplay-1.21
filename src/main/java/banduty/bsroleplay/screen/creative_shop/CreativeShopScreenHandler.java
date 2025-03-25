
package banduty.bsroleplay.screen.creative_shop;

import banduty.bsroleplay.block.entity.shops.CreativeShopBlockEntity;
import banduty.bsroleplay.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class CreativeShopScreenHandler extends ScreenHandler {
    private final Inventory sellInventory;
    private final PropertyDelegate propertyDelegateLower;
    private final PropertyDelegate propertyDelegateUpper;
    public final CreativeShopBlockEntity blockEntity;

    public CreativeShopScreenHandler(int syncId, PlayerInventory playerInventory, CreativeShopBlockEntity.Data data) {
        this(syncId, playerInventory, data, new ArrayPropertyDelegate(1), new ArrayPropertyDelegate(1));
    }

    public CreativeShopScreenHandler(int syncId, PlayerInventory playerInventory, CreativeShopBlockEntity.Data data, PropertyDelegate propertyDelegateLower, PropertyDelegate propertyDelegateUpper) {
        super(ModScreenHandlers.CREATIVE_SHOP_SCREEN_HANDLER, syncId);
        BlockEntity blockEntity = playerInventory.player.getWorld().getBlockEntity(data.blockPos());
        this.blockEntity = ((CreativeShopBlockEntity) blockEntity);
        this.sellInventory = ((Inventory) blockEntity);
        if (sellInventory != null) sellInventory.onOpen(playerInventory.player);
        this.propertyDelegateLower = propertyDelegateLower;
        this.propertyDelegateUpper = propertyDelegateUpper;

        this.addSlot(new Slot(this.sellInventory, 0, 20, 60));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(this.propertyDelegateLower);
        addProperties(this.propertyDelegateUpper);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return super.onButtonClick(player, id);
    }

    public void setCurrencyCounter(int value) {
        if (value >= 0) {
            this.blockEntity.setCurrencyCounter(value);
            this.propertyDelegateLower.set(0, value & 0xFFFF);
            this.propertyDelegateUpper.set(0, (value >> 16) & 0xFFFF);
        }
    }

    public int getCurrencyCounter() {
        int lower = this.propertyDelegateLower.get(0);
        int upper = this.propertyDelegateUpper.get(0);
        return (upper << 16) | (lower & 0xFFFF);
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
            if (1 <= slotIndex) {
                if (!this.insertItem(newStack, 2, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(newStack, originalStack);
            } else if (!this.insertItem(newStack, 2, 37, false)) return ItemStack.EMPTY;
            if (newStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (newStack.getCount() == originalStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, newStack);

            if (1 <= slotIndex) {
                player.dropItem(newStack, false);
            }
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
}
