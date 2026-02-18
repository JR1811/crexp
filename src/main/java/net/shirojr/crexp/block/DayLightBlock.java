package net.shirojr.crexp.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.DaylightDetectorBlock;
import net.shirojr.crexp.block.util.AbstractLightBlock;

public class DayLightBlock extends AbstractLightBlock {
    public static final MapCodec<DayLightBlock> CODEC = createCodec(DayLightBlock::new);

    public DayLightBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public boolean isPoweredOnDay() {
        return true;
    }
}
