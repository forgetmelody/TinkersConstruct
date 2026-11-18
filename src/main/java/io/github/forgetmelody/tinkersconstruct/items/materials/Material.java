package io.github.forgetmelody.tinkersconstruct.items.materials;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolPart;
import io.github.forgetmelody.tinkersconstruct.items.traits.Trait;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;

public record Material(
        int color,
        HolderSet<Trait> traits
) {
    public static final Codec<Material> DIRECT_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("color").forGetter(Material::color),
            Trait.LIST_CODEC.fieldOf("traits").forGetter(Material::traits)
    ).apply(instance, Material::new));
    public static final Codec<Holder<Material>> REFERENCE_CODEC = RegistryFixedCodec.create(Registries.MATERIAL);
    public static final Codec<HolderSet<Material>> LIST_CODEC = RegistryCodecs.homogeneousList(Registries.MATERIAL);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Material>> STREAM_CODEC = ByteBufCodecs.holderRegistry(Registries.MATERIAL);
}
