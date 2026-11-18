package io.github.forgetmelody.tinkersconstruct.items.traits;

import com.mojang.serialization.Codec;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.world.item.enchantment.Enchantment;

import java.awt.*;

public record Trait(Component description, DataComponentMap effects, int maxLevel) {
    public static final Codec<Holder<Trait>> REFERENCE_CODEC = RegistryFixedCodec.create(Registries.TRAIT);
    public static final Codec<HolderSet<Trait>> LIST_CODEC = RegistryCodecs.homogeneousList(Registries.TRAIT);
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<Trait>> STREAM_CODEC = ByteBufCodecs.holderRegistry(Registries.TRAIT);

    public static Component getFullName(Holder<Trait> enchantment, int level) {
        MutableComponent result = enchantment.value().description().copy();

        if (level != 1 || enchantment.value().maxLevel() != 1) {
            result.append(CommonComponents.SPACE).append(Component.translatable("tinkers_construct.traits.level." + level));
        }

        return result;
    }
}
