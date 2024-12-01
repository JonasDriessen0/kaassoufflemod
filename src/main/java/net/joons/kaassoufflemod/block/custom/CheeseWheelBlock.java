package net.joons.kaassoufflemod.block.custom;

import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheeseWheelBlock extends CakeBlock {
    public CheeseWheelBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);

        if (heldItem.getItem() == ModItems.CHEESE_SLICER) {
            if (!world.isClient) {
                int bites = state.get(BITES);

                ItemStack cheeseSlice = new ItemStack(ModItems.CHEESE_SLICE, 3);
                dropStack(world, pos, cheeseSlice);

                world.playSound(null, pos, SoundEvents.BLOCK_CAKE_ADD_CANDLE, SoundCategory.BLOCKS, 1.0f, 1.0f);

                if (bites < MAX_BITES - 1) {
                    world.setBlockState(pos, state.with(BITES, bites + 1), NOTIFY_ALL);
                } else {
                    world.removeBlock(pos, false);
                }
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
