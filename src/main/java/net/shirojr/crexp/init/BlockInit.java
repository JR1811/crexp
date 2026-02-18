package net.shirojr.crexp.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.shirojr.crexp.CreativelyExpressed;
import net.shirojr.crexp.block.DayLightBlock;
import net.shirojr.crexp.block.NightLightBlock;
import net.shirojr.crexp.block.util.AbstractLightBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings("unused")
public interface BlockInit {
    List<Block> ALL_BLOCKS = new ArrayList<>();

    DayLightBlock LIGHT_DAY = register("light_day", new DayLightBlock(
                    AbstractBlock.Settings.create()
                            .replaceable()
                            .strength(-1.0F, 3600000.8F)
                            .mapColor(createMapColorFromWaterloggedBlockState(MapColor.CLEAR))
                            .dropsNothing()
                            .nonOpaque()
                            .luminance(AbstractLightBlock.STATE_TO_LUMINANCE)
            ), true
    );
    NightLightBlock LIGHT_NIGHT = register("light_night", new NightLightBlock(
                    AbstractBlock.Settings.create()
                            .replaceable()
                            .strength(-1.0F, 3600000.8F)
                            .mapColor(createMapColorFromWaterloggedBlockState(MapColor.CLEAR))
                            .dropsNothing()
                            .nonOpaque()
                            .luminance(AbstractLightBlock.STATE_TO_LUMINANCE)
            ), true
    );

    @SuppressWarnings("SameParameterValue")
    private static Function<BlockState, MapColor> createMapColorFromWaterloggedBlockState(MapColor mapColor) {
        return state -> state.get(Properties.WATERLOGGED) ? MapColor.WATER_BLUE : mapColor;
    }

    @SuppressWarnings("SameParameterValue")
    private static <T extends Block> T register(String name, T entry, boolean registerItem) {
        T registeredBlock = Registry.register(Registries.BLOCK, CreativelyExpressed.getId(name), entry);
        if (registerItem) {
            ItemInit.register(name, new BlockItem(registeredBlock, new Item.Settings()));
        }
        return registeredBlock;
    }

    static void initialize() {
        // static initialisation
    }
}
