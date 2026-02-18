package net.shirojr.crexp.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.shirojr.crexp.CreativelyExpressed;
import net.shirojr.crexp.init.BlockInit;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class TranslationGenerator extends FabricLanguageProvider {
    public TranslationGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder builder) {
        builder.add(BlockInit.LIGHT_DAY, "Day Light Block");
        builder.add(BlockInit.LIGHT_NIGHT, "Night Light Block");
        builder.add("itemGroup.crexp.creatively_expressed", "Creatively Expressed");

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/%s/lang/en_us.existing.json".formatted(CreativelyExpressed.MOD_ID)).orElseThrow();
            builder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
