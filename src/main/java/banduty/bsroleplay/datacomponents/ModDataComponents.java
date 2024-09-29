package banduty.bsroleplay.datacomponents;

import banduty.bsroleplay.BsRolePlay;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.BlockPos;

public class ModDataComponents {
    public static final ComponentType<BlockPos> BLOCKPOS = register("blockpos",
            ComponentType.<BlockPos>builder().codec(BlockPos.CODEC).packetCodec(BlockPos.PACKET_CODEC).build());

    public static final ComponentType<Integer> MONEY = register("money",
            ComponentType.<Integer>builder().codec(Codec.INT).packetCodec(PacketCodecs.INTEGER).build());

    public static final ComponentType<Integer> CHARGES = register("charges",
            ComponentType.<Integer>builder().codec(Codec.INT).packetCodec(PacketCodecs.INTEGER).build());

    private static <T> ComponentType<T> register(String name, ComponentType<T> builder) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, BsRolePlay.identifierOf(name), builder);
    }

    public static void register() {}
}
