package io.github.forgetmelody.tinkersconstruct.items.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.forgetmelody.tinkersconstruct.items.materials.Material;
import io.github.forgetmelody.tinkersconstruct.items.traits.Trait;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record ToolDefinition(
        DataComponentMap effects,
        HolderSet<Trait> traits,
        HolderSet<Material> materials
) {
    public static final Codec<ToolDefinition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ToolEffectComponents.CODEC.fieldOf("effects").forGetter(ToolDefinition::effects),
            Trait.LIST_CODEC.fieldOf("traits").forGetter(ToolDefinition::traits),
            Material.LIST_CODEC.fieldOf("materials").forGetter(ToolDefinition::materials)
    ).apply(instance, ToolDefinition::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ToolDefinition> STREAM_CODEC = ByteBufCodecs.registry(Registries.TOOL);
    public static final ToolDefinition EMPTY = new ToolDefinition(DataComponentMap.EMPTY, HolderSet.empty(),  HolderSet.empty());
}
