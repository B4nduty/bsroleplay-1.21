package banduty.bsroleplay.datagen;

import banduty.bsroleplay.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.DYEABLE)
                .add(ModItems.WALLET)
                .add(ModItems.PIRATE_HELMET)
                .add(ModItems.PIRATE_CHESTPLATE)
                .add(ModItems.PIRATE_LEGGINGS)
                .add(ModItems.PROTECTION_HELMET)
                .add(ModItems.PROTECTION_CHESTPLATE)
                .add(ModItems.PROTECTION_LEGGINGS)
                .add(ModItems.PROTECTION_BOOTS)
                .add(ModItems.GADGET_HAT);
    }
}