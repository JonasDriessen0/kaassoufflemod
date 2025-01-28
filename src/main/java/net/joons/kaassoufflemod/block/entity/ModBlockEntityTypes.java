package net.joons.kaassoufflemod.block.entity;

import net.joons.kaassoufflemod.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityTypes {
    public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of("kaassoufflemod", path), blockEntityType);
    }

    public static final BlockEntityType<PressBlockEntity> PRESS = register(
            "press",
            BlockEntityType.Builder.create(PressBlockEntity::new, ModBlocks.PRESS).build()
    );

    public static void initialize() {
    }
}
