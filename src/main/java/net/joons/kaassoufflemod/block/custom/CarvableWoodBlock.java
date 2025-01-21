package net.joons.kaassoufflemod.block.custom;

import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class CarvableWoodBlock extends CakeBlock {
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0),  // Full height (no bites)
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0),  // 1 bite
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 12.0, 13.0),  // 2 bites
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 10.0, 13.0),  // 3 bites
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 8.0, 13.0),   // 4 bites
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 6.0, 13.0),   // 5 bites
            Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)    // 6 bites
    };

    public CarvableWoodBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(BITES)];
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            // Get the current number of bites
            int bites = state.get(BITES);
            ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);

            if (heldItem.getItem() == ModItems.SPOON){
                if (bites < 6) {
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK,
                            SoundCategory.BLOCKS, 1.0f, 1.0f);

                    world.setBlockState(pos, state.with(BITES, bites + 1));

                    return ActionResult.SUCCESS;
                } else {
                    // Break the block when fully carved (like cake)
                    world.removeBlock(pos, false);
                    world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK,
                            SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        }
        return ActionResult.CONSUME;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES[state.get(BITES)];
    }
}