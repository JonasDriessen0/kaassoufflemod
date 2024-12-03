package net.joons.kaassoufflemod.block.custom;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.block.ShapeContext;
import net.minecraft.world.World;

public class RawCheeseBlock extends Block {
    public RawCheeseBlock(Settings settings) {
        super(settings);
    }

    private static final VoxelShape SHAPE = createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit){
        ItemStack heldItem = player.getStackInHand(Hand.MAIN_HAND);

        if (heldItem.isOf(Items.BUCKET)) {
            if (!world.isClient) {
                world.setBlockState(pos, net.minecraft.block.Blocks.AIR.getDefaultState());

                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.BUCKET_OF_RAW_CHEESE));

                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (!world.isClient && entity instanceof PlayerEntity player) {
            ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
            if(boots.getItem() == Items.IRON_BOOTS){
                world.setBlockState(pos, ModBlocks.CHEESE_WHEEL.getDefaultState());
            }
        }

        super.onSteppedOn(world, pos, state, entity);
    }
}
