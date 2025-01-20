package net.joons.kaassoufflemod.item.custom;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CheeseBucketItem extends Item {
    public CheeseBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context){
        World world = context.getWorld();

        if(!world.isClient){
            BlockPos clickedBlockPos = context.getBlockPos();

            BlockPos newBlockPos = clickedBlockPos.up(1);

            BlockState blockToPlace = ModBlocks.RAW_CHEESE.getDefaultState();

            if (world.isAir(newBlockPos)) { // Check if the position is empty
                world.setBlockState(newBlockPos, blockToPlace);

                world.playSound(null, context.getBlockPos(), SoundEvents.BLOCK_WET_SPONGE_PLACE, SoundCategory.BLOCKS);

                context.getPlayer().setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));

                return ActionResult.SUCCESS;
            }

        }
        return ActionResult.PASS;
    }
}
