
package banduty.bsroleplay.screen.strongbox;

import banduty.bsroleplay.block.entity.StrongboxBlockEntity;
import banduty.bsroleplay.screen.ModScreenHandlers;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class StrongboxScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    public final StrongboxBlockEntity blockEntity;

    public StrongboxScreenHandler(int syncId, PlayerInventory playerInventory, StrongboxBlockEntity.Data data) {
        this(syncId, playerInventory, data.blockPos());
    }

    public StrongboxScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos blockPos) {
        super(ModScreenHandlers.STRONGBOX_SCREEN_HANDLER, syncId);
        BlockEntity blockEntity = playerInventory.player.getWorld().getBlockEntity(blockPos);
        this.blockEntity = ((StrongboxBlockEntity) blockEntity);
        this.inventory = ((Inventory) blockEntity);
        if (inventory != null) inventory.onOpen(playerInventory.player);

        this.addSlot(new Slot(this.inventory, 0, 71, 25));
        this.addSlot(new Slot(this.inventory, 1, 89, 25));
        this.addSlot(new Slot(this.inventory, 2, 71, 43));
        this.addSlot(new Slot(this.inventory, 3, 89, 43));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {
        return super.onButtonClick(player, id);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        blockEntity.setOpen(false);
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
            if (1 <= slotIndex && slotIndex < 4) {
                if (!this.insertItem(newStack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(newStack, originalStack);
            } else if (4 <= slotIndex && slotIndex < 40 ?
                            (slotIndex < 32 ?
                                    !this.insertItem(newStack, 31, 40, false) :
                                    !this.insertItem(newStack, 4, 31, false)) :
                    !this.insertItem(newStack, 4, 40, false)
            ) {
                return ItemStack.EMPTY;
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

            if (1 <= slotIndex && slotIndex < 4) {
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
