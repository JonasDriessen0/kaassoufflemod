package net.joons.kaassoufflemod.event;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.joons.kaassoufflemod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.AxeItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class BlockInteractionHandler {
    public static void register() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            BlockPos pos = hitResult.getBlockPos();

            if (player.getMainHandStack().getItem() instanceof AxeItem) {
                if (world.getBlockState(pos).getBlock() == Blocks.STRIPPED_OAK_LOG) {
                    if (!world.isClient) {
                        world.setBlockState(pos, ModBlocks.CARVABLE_WOOD.getDefaultState());

                        // Optional: Play axe stripping sound
                        world.playSound(null, pos,
                                net.minecraft.sound.SoundEvents.ITEM_AXE_STRIP,
                                net.minecraft.sound.SoundCategory.BLOCKS,
                                1.0F, 1.0F);
                    }
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }
}