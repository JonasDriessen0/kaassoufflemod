package net.joons.kaassoufflemod.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.joons.kaassoufflemod.item.ModItems;
import net.joons.kaassoufflemod.screen.PressScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class PressBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    private static final int TOP_INPUT_SLOT = 0;
    private static final int MAIN_INPUT_SLOT = 1;
    private static final int BOTTOM_INPUT_SLOT = 2;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public PressBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.PRESS, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> PressBlockEntity.this.progress;
                    case 1 -> PressBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> PressBlockEntity.this.progress = value;
                    case 1 -> PressBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, false, registryLookup);
        nbt.putInt("press_progress", progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, false, registryLookup);
        progress = nbt.getInt("press_progress");
    }

    protected static final RawAnimation DEPLOY_ANIM = RawAnimation.begin().thenPlay("animation.press.dopress").thenLoop("animation.press.dopress");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::deployAnimController));
    }

    protected <E extends PressBlockEntity> PlayState deployAnimController(final AnimationState<E> state) {
        return state.setAndContinue(DEPLOY_ANIM);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private static final ItemStack LEAD_PLATE = new ItemStack(ModItems.LEAD_PLATE);
    private static final ItemStack OBSIDIAN_DUST = new ItemStack(ModItems.OBSIDIAN_DUST);
    private static final ItemStack STEEL_INGOT = new ItemStack(ModItems.STEEL_INGOT);
    private static final ItemStack LEAD_INGOT = new ItemStack(ModItems.LEAD_INGOT);
    private static final ItemStack IRON_INGOT = new ItemStack(Items.IRON_INGOT);
    private static final ItemStack OBSIDIAN_INFUSED_INGOT = new ItemStack(ModItems.OBSIDIAN_INFUSED_INGOT);

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()){
            return;
        }

        if (this.hasRecipe()){
            this.increaseCraftProgress();
            markDirty(world, pos, state);

            if(hasCraftingFinished()){
                this.craftItem();
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        ItemStack topInput = getStack(TOP_INPUT_SLOT);
        ItemStack mainInput = getStack(MAIN_INPUT_SLOT);
        ItemStack bottomInput = getStack(BOTTOM_INPUT_SLOT);

        if (topInput.getItem() == OBSIDIAN_DUST.getItem() &&
                mainInput.getItem() == STEEL_INGOT.getItem() &&
                bottomInput.getItem() == OBSIDIAN_DUST.getItem()) {
            this.removeStack(TOP_INPUT_SLOT, 1);
            this.removeStack(MAIN_INPUT_SLOT, 1);
            this.removeStack(BOTTOM_INPUT_SLOT, 1);
            ItemStack result = new ItemStack(ModItems.OBSIDIAN_INFUSED_INGOT);
            this.setStack(MAIN_INPUT_SLOT, new ItemStack(result.getItem(), getStack(MAIN_INPUT_SLOT).getCount() + result.getCount()));
        } else if (topInput.getItem() == IRON_INGOT.getItem() &&
                mainInput.getItem() == OBSIDIAN_INFUSED_INGOT.getItem() &&
                bottomInput.getItem() == IRON_INGOT.getItem()) {
            this.removeStack(TOP_INPUT_SLOT, 1);
            this.removeStack(MAIN_INPUT_SLOT, 1);
            this.removeStack(BOTTOM_INPUT_SLOT, 1);
            ItemStack result = new ItemStack(ModItems.REINFORCED_INGOT);
            this.setStack(MAIN_INPUT_SLOT, new ItemStack(result.getItem(), getStack(MAIN_INPUT_SLOT).getCount() + result.getCount()));
        } else if (mainInput.getItem() == LEAD_INGOT.getItem()) {
            // Recipe 3: LEAD_INGOT in the middle (original recipe)
            this.removeStack(MAIN_INPUT_SLOT, 1);
            ItemStack result = new ItemStack(ModItems.LEAD_PLATE);
            this.setStack(MAIN_INPUT_SLOT, new ItemStack(result.getItem(), getStack(MAIN_INPUT_SLOT).getCount() + result.getCount()));
        }
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack topInput = getStack(TOP_INPUT_SLOT);
        ItemStack mainInput = getStack(MAIN_INPUT_SLOT);
        ItemStack bottomInput = getStack(BOTTOM_INPUT_SLOT);

        // Recipe 1: OBSIDIAN_DUST on top and bottom, LEAD_INGOT in the middle
        boolean recipe1 = topInput.getItem() == OBSIDIAN_DUST.getItem() &&
                mainInput.getItem() == STEEL_INGOT.getItem() &&
                bottomInput.getItem() == OBSIDIAN_DUST.getItem();

        // Recipe 2: IRON_INGOT on top and bottom, OBSIDIAN_INFUSED_INGOT in the middle
        boolean recipe2 = topInput.getItem() == IRON_INGOT.getItem() &&
                mainInput.getItem() == OBSIDIAN_INFUSED_INGOT.getItem() &&
                bottomInput.getItem() == IRON_INGOT.getItem();

        // Recipe 3: LEAD_INGOT in the middle (original recipe)
        boolean recipe3 = mainInput.getItem() == STEEL_INGOT.getItem();

        return recipe1 || recipe2 || recipe3;
    }

    @Override
    public Object getScreenOpeningData(ServerPlayerEntity serverPlayerEntity) {
        return new PressData(this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Press");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PressScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
