package io.github.forgetmelody.tinkersconstruct.tags;

import io.github.forgetmelody.tinkersconstruct.TinkersConstruct;
import io.github.forgetmelody.tinkersconstruct.items.traits.Trait;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.tags.TagKey;

public final class TraitTags {
    public static final TagKey<Trait> TOOLTIP_ORDER = key("tooltip_order");

    private TraitTags() {
    }

    private static TagKey<Trait> key(String name) {
        return TagKey.create(Registries.TRAIT, TinkersConstruct.id(name));
    }
}
