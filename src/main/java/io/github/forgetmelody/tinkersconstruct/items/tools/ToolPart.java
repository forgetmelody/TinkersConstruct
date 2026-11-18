package io.github.forgetmelody.tinkersconstruct.items.tools;

import com.mojang.serialization.Codec;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

public record ToolPart() {
    public static final Codec<Holder<ToolPart>> REFERENCE_CODEC = RegistryFixedCodec.create(Registries.TOOL_PART);
    public static final Codec<HolderSet<ToolPart>> LIST_CODEC = RegistryCodecs.homogeneousList(Registries.TOOL_PART);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<ToolPart>> STREAM_CODEC = ByteBufCodecs.holderRegistry(Registries.TOOL_PART);
}
