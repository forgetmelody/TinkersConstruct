package io.github.forgetmelody.tinkersconstruct.items.traits;

import io.github.forgetmelody.tinkersconstruct.components.DataComponents;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public final class TraitHelper {
    private TraitHelper() {
    }

    public static void runIterationOnItem(ItemStack item, TraitVisitor visitor) {
        for (Reference2IntMap.Entry<Holder<Trait>> entry : item.getOrDefault(DataComponents.TRAITS, ItemTraits.EMPTY).entrySet()) {
            visitor.accept(entry.getKey(), entry.getIntValue());
        }
    }

    public static Item.Properties traits(Item.Properties properties, ItemTraits traits) {
        return properties.component(DataComponents.TRAITS, traits);
    }

    @FunctionalInterface
    public interface TraitVisitor {
        void accept(Holder<Trait> trait, int level);
    }
}
