package net.shirojr.crexp.init;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.shirojr.crexp.CreativelyExpressed;
import net.shirojr.crexp.blockentity.DayNightLightBlockEntity;

public interface BlockEntityTypeInit {
    BlockEntityType<DayNightLightBlockEntity> DAY_NIGHT_LIGHT = register(
            "day_night_light",
            DayNightLightBlockEntity::new,
            BlockInit.LIGHT_DAY, BlockInit.LIGHT_NIGHT
    );


    @SuppressWarnings("SameParameterValue")
    private static <T extends BlockEntity> BlockEntityType<T> register(String name,
                                                                       BlockEntityType.BlockEntityFactory<? extends T> entityFactory,
                                                                       Block... blocks) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, CreativelyExpressed.getId(name),
                BlockEntityType.Builder.<T>create(entityFactory, blocks).build());
    }

    static void initialize() {
        // static initialisation
    }
}
