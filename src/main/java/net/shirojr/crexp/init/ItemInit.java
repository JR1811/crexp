package net.shirojr.crexp.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.shirojr.crexp.CreativelyExpressed;

import java.util.ArrayList;
import java.util.List;

public interface ItemInit {
    List<Item> ALL_ITEMS = new ArrayList<>();

    @SuppressWarnings("UnusedReturnValue")
    static <T extends Item> T register(String name, T entry) {
        T registeredEntry = Registry.register(Registries.ITEM, CreativelyExpressed.getId(name), entry);
        ALL_ITEMS.add(registeredEntry);
        return registeredEntry;
    }

    static void initialize() {
        // static initialisation
    }
}
