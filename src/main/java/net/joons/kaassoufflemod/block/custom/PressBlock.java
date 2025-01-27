package net.joons.kaassoufflemod.block.custom;

import com.mojang.serialization.MapCodec;
import net.joons.kaassoufflemod.entity.ModBlockEntityTypes;
import net.joons.kaassoufflemod.entity.PressBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PressBlock extends BlockWithEntity {
    public PressBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends PressBlock> getCodec() {
        return createCodec(PressBlock::new);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PressBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        // Make sure to check world.isClient if you only want to tick only on serverside.
        return validateTicker(type, ModBlockEntityTypes.PRESS, PressBlockEntity::tick);
    }
}
