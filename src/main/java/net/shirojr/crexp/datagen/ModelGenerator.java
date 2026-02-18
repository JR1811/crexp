package net.shirojr.crexp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.shirojr.crexp.block.DayLightBlock;
import net.shirojr.crexp.block.NightLightBlock;
import net.shirojr.crexp.init.BlockInit;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        DayLightBlock lightDayBlock = BlockInit.LIGHT_DAY;
        generator.registerSimpleCubeAll(lightDayBlock);
        generator.excludeFromSimpleItemModelGeneration(lightDayBlock);

        NightLightBlock lightNightBlock = BlockInit.LIGHT_NIGHT;
        generator.registerSimpleCubeAll(lightNightBlock);
        generator.excludeFromSimpleItemModelGeneration(lightNightBlock);
    }

    @Override
    public void generateItemModels(ItemModelGenerator generator) {
        generator.register(BlockInit.LIGHT_DAY.asItem(), Models.GENERATED);
        generator.register(BlockInit.LIGHT_NIGHT.asItem(), Models.GENERATED);
    }
}
