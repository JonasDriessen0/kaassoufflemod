package net.joons.kaassoufflemod.mixin;

import net.joons.kaassoufflemod.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class DetectJump {
    private int jumpCount = 0;

    @Inject(method = "jump", at = @At("HEAD"))
    public void onJump(CallbackInfo info){
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getMainHandStack().getItem() == Items.MILK_BUCKET) {
            jumpCount++;
            World world = player.getWorld();

            if(jumpCount >= 7){
                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(ModItems.BUCKET_OF_RAW_CHEESE));
                if(!world.isClient()){
                    world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS);
                }
            }

        } else {
            jumpCount = 0;
        }
    }
}
