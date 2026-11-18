package io.github.forgetmelody.tinkersconstruct.registries;

import io.github.forgetmelody.tinkersconstruct.TinkersConstruct;
import io.github.forgetmelody.tinkersconstruct.items.materials.Material;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolDefinition;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolPart;
import io.github.forgetmelody.tinkersconstruct.items.traits.Trait;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;

public final class Registries {
    public static final ResourceKey<Registry<DataComponentType<?>>> TOOL_EFFECT_COMPONENT_TYPE = key("tool_effect_component_type");
    public static final ResourceKey<Registry<DataComponentType<?>>> TRAIT_EFFECT_COMPONENT_TYPE = key("trait_effect_component_type");
    // Dynamic
    public static final ResourceKey<Registry<ToolPart>> TOOL_PART = key("tool_part");
    public static final ResourceKey<Registry<ToolDefinition>> TOOL = key("tool");
    public static final ResourceKey<Registry<Trait>> TRAIT = key("trait");
    public static final ResourceKey<Registry<Material>> MATERIAL = key("material");

    private Registries() {
    }

    private static <T> ResourceKey<Registry<T>> key(String name) {
        return ResourceKey.createRegistryKey(TinkersConstruct.id(name));
    }
}
