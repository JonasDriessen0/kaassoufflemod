package net.joons.kaassoufflemod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class ClogBlock extends Block {
    public ClogBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = createCuboidShape(5.6, 0, 3.5, 10.4, 5, 12.5);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
