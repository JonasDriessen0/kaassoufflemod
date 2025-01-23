package net.joons.kaassoufflemod.block.custom;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ClogBlock extends Block {
    public ClogBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = createCuboidShape(5.6, 0, 3.5, 10.4, 5, 12.5);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit){
        ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);

        if (heldItem.isOf(ModItems.PAINTBRUSH)) {
            if (!world.isClient) {
                world.setBlockState(pos, ModBlocks.CLOG_BLOCK.getDefaultState());
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }
}
