package net.joons.kaassoufflemod.block.entity;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.util.math.BlockPos;

public record PressData(BlockPos pos) {
    public static final PacketCodec<RegistryByteBuf, PressData> PACKET_CODEC =
            PacketCodec.tuple(BlockPos.PACKET_CODEC, PressData::pos, PressData::new);
}
