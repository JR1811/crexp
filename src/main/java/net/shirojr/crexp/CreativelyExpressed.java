package net.shirojr.crexp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.shirojr.crexp.init.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreativelyExpressed implements ModInitializer {
    public static final String MOD_ID = "crexp";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ItemInit.initialize();
        BlockInit.initialize();
        ItemGroupInit.initialize();
        BlockEntityTypeInit.initialize();
        GameRulesInit.initialize();

        LOGGER.info("Finished Creative Expressionism!");
    }

    public static Identifier getId(String path) {
        return Identifier.of(MOD_ID, path);
    }
}