package io.github.forgetmelody.tinkersconstruct.registries;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.RegistryBuilder;

public final class BuiltInRegistries {
    public static final Registry<DataComponentType<?>> TOOL_EFFECT_COMPONENT_TYPE = create(Registries.TOOL_EFFECT_COMPONENT_TYPE);
    public static final Registry<DataComponentType<?>> TRAIT_EFFECT_COMPONENT_TYPE = create(Registries.TRAIT_EFFECT_COMPONENT_TYPE);

    private BuiltInRegistries() {
    }

    private static <T> Registry<T> create(ResourceKey<Registry<T>> registryKey) {
        return new RegistryBuilder<>(registryKey).create();
    }
}
