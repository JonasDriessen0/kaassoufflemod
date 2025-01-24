package net.joons.kaassoufflemod.item.custom;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.component.ModDataComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PaintbrushItem extends Item {
    public PaintbrushItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Block clickedBlock = world.getBlockState(pos).getBlock();
        ItemStack paintbrush = context.getStack();

        if (clickedBlock == Blocks.DANDELION) {
            paintbrush.set(ModDataComponentTypes.TEXTURE_TYPE, true);
            return ActionResult.SUCCESS;
        }

        if (clickedBlock == ModBlocks.UNPAINTED_CLOG) {
            if (Boolean.TRUE.equals(paintbrush.get(ModDataComponentTypes.TEXTURE_TYPE))) {
                if (!world.isClient) {
                    world.setBlockState(pos, ModBlocks.CLOG_BLOCK.getDefaultState());
                    paintbrush.remove(ModDataComponentTypes.TEXTURE_TYPE);
                }
                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }
}