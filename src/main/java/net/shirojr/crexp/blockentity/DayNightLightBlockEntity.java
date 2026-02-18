package net.shirojr.crexp.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.shirojr.crexp.init.BlockEntityTypeInit;

public class DayNightLightBlockEntity extends BlockEntity {
    public DayNightLightBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityTypeInit.DAY_NIGHT_LIGHT, pos, state);
    }
}
