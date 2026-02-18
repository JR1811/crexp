package net.shirojr.crexp.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockWithEntity;
import net.shirojr.crexp.block.util.AbstractLightBlock;

public class NightLightBlock extends AbstractLightBlock {
    public static final MapCodec<NightLightBlock> CODEC = createCodec(NightLightBlock::new);

    public NightLightBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public boolean isPoweredOnDay() {
        return false;
    }
}
