package net.shirojr.crexp.block.util;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.shirojr.crexp.blockentity.DayNightLightBlockEntity;
import net.shirojr.crexp.init.BlockEntityTypeInit;
import net.shirojr.crexp.init.GameRulesInit;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public abstract class AbstractLightBlock extends BlockWithEntity implements Waterloggable {
    public static final BooleanProperty POWERED = Properties.POWERED;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(POWERED) ? 15 : 0;


    public AbstractLightBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(POWERED, false)
                .with(WATERLOGGED, false)
        );
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DayNightLightBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient) return null;
        if (!world.getDimension().hasSkyLight()) return null;
        return validateTicker(type, BlockEntityTypeInit.DAY_NIGHT_LIGHT, AbstractLightBlock::tick);
    }

    protected static void tick(World world, BlockPos pos, BlockState state, DayNightLightBlockEntity blockEntity) {
        if (!(state.getBlock() instanceof AbstractLightBlock abstractLightBlock)) return;
        int checkInterval = world.getGameRules().getInt(GameRulesInit.DAY_NIGHT_LIGHT_CHECK_INTERVAL);
        if (checkInterval != 1 && world.getTime() % checkInterval != 0) return;
        boolean newPowered = world.isDay() == abstractLightBlock.isPoweredOnDay();
        world.setBlockState(pos, state.with(POWERED, newPowered), Block.NOTIFY_ALL);
    }

    public abstract boolean isPoweredOnDay();

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(POWERED, WATERLOGGED);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world instanceof ServerWorld serverWorld && player.isCreativeLevelTwoOp()) {
            BlockState blockState = world.getBlockState(pos);
            if (!blockState.contains(POWERED) || !(blockState.getBlock() instanceof AbstractLightBlock)) {
                return ActionResult.FAIL;
            }
            serverWorld.setBlockState(pos, blockState.with(POWERED, !blockState.get(POWERED)));
            return ActionResult.SUCCESS;
        }

        return ActionResult.CONSUME;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return context.isHolding(this.asItem()) ? VoxelShapes.fullCube() : VoxelShapes.empty();
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }
}
