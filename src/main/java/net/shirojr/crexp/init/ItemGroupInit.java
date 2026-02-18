package net.shirojr.crexp.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.shirojr.crexp.CreativelyExpressed;

public class ItemGroupInit {
    public static final RegistryKey<ItemGroup> ITEM_GROUP = register("crexp",
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(BlockInit.LIGHT_DAY.asItem()))
                    .displayName(Text.translatable("itemGroup.crexp.creatively_expressed"))
                    .build());

    static {
        ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP).register(entries -> {
            for (Item registeredItem : ItemInit.ALL_ITEMS) {
                entries.add(registeredItem.getDefaultStack());
            }
        });
    }

    @SuppressWarnings("SameParameterValue")
    private static RegistryKey<ItemGroup> register(String name, ItemGroup group) {
        Registry.register(Registries.ITEM_GROUP, CreativelyExpressed.getId(name), group);
        return RegistryKey.of(Registries.ITEM_GROUP.getKey(), CreativelyExpressed.getId(name));
    }

    public static void initialize() {
        // static initialisation
    }
}
