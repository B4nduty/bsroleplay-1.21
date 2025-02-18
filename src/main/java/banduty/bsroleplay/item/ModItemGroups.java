package banduty.bsroleplay.item;

import banduty.bsroleplay.BsRolePlay;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> ITEMS = registerItemGroup("bsroleplay_item", () -> ModItems.ROLEPLAY_CORE);
    public static final RegistryKey<ItemGroup> BLOCKS = registerItemGroup("bsroleplay_block", () -> ModItems.TINY_BANDUTY_ITEM);
    public static final RegistryKey<ItemGroup> ARMOR = registerItemGroup("bsroleplay_armor", () -> ModItems.CIVIC_CROWN);

    public static RegistryKey<ItemGroup> registerItemGroup(String name, Supplier<Item> icon) {
        Text displayName = Text.translatable("itemgroup.%s.%s".formatted(BsRolePlay.MOD_ID, name));
        ItemGroup itemGroup = FabricItemGroup.builder().icon(() -> new ItemStack(icon.get())).displayName(displayName).build();
        Registry.register(Registries.ITEM_GROUP, getRegistryKey(name), itemGroup);
        return getRegistryKey(name);
    }

    private static RegistryKey<ItemGroup> getRegistryKey(String name) {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, BsRolePlay.identifierOf(name));
    }

    public static void registerItemGroups() {
        BsRolePlay.LOGGER.info("Registering Item Groups for " + BsRolePlay.MOD_ID);
    }
}
