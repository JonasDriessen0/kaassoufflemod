package net.joons.kaassoufflemod.block.custom;

import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PaintedClogBlock extends Block {
    public PaintedClogBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = createCuboidShape(5.6, 0, 3.5, 10.4, 5, 12.5);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
