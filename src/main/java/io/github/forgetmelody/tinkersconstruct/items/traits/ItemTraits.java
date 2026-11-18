package io.github.forgetmelody.tinkersconstruct.items.traits;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.forgetmelody.tinkersconstruct.tags.TraitTags;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ItemTraits implements TooltipProvider {
    public static final Codec<ItemTraits> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(Trait.REFERENCE_CODEC, Codec.intRange(1, 255)).fieldOf("traits").forGetter(ItemTraits::traits)
    ).apply(instance, ItemTraits::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemTraits> STREAMCODEC = StreamCodec.composite(
            ByteBufCodecs.map(Reference2IntArrayMap::new, Trait.STREAM_CODEC, ByteBufCodecs.VAR_INT),
            ItemTraits::traits,
            ItemTraits::new
    );
    public static final ItemTraits EMPTY = new ItemTraits(Reference2IntMaps.emptyMap());
    private final Reference2IntMap<Holder<Trait>> traits;

    private ItemTraits(Map<Holder<Trait>, Integer> traits) {
        this.traits = new Reference2IntArrayMap<>(traits);
    }

    private Reference2IntMap<Holder<Trait>> traits() {
        return traits;
    }

    private ItemTraits(Reference2IntMap<Holder<Trait>> traits) {
        this.traits = traits;
    }

    public int getLevel(Holder<Trait> trait) {
        return this.traits.getInt(trait);
    }

    public Set<Holder<Trait>> keySet() {
        return Collections.unmodifiableSet(this.traits.keySet());
    }

    public Set<Reference2IntMap.Entry<Holder<Trait>>> entrySet() {
        return Collections.unmodifiableSet(this.traits.reference2IntEntrySet());
    }

    public ItemTraits.Mutable mutable() {
        return new ItemTraits.Mutable(new Reference2IntOpenHashMap<>(this.traits));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemTraits) obj;
        return Objects.equals(this.traits, that.traits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traits);
    }

    @Override
    public String toString() {
        return "ItemTraits[" +
                "traits=" + traits + ']';
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> adder, TooltipFlag flag, DataComponentGetter components) {
        HolderLookup.Provider registries = context.registries();
        HolderSet<Trait> order = getTagOrEmpty(registries);

        for (Holder<Trait> trait : order) {
            int level = this.traits.getInt(trait);
            if (level > 0) {
                adder.accept(Trait.getFullName(trait, level));
            }
        }

        for (Reference2IntMap.Entry<Holder<Trait>> entry : this.traits.reference2IntEntrySet()) {
            Holder<Trait> trait = entry.getKey();
            if (!order.contains(trait)) {
                adder.accept(Trait.getFullName(entry.getKey(), entry.getIntValue()));
            }
        }
    }

    private static HolderSet<Trait> getTagOrEmpty(HolderLookup.@Nullable Provider registries) {
        if (registries != null) {
            registries.get(TraitTags.TOOLTIP_ORDER);
        }

        return HolderSet.empty();
    }

    public static class Mutable {
        private final Reference2IntMap<Holder<Trait>> traits;

        public Mutable(Reference2IntMap<Holder<Trait>> traits) {
            this.traits = traits;
        }

        public void set(Holder<Trait> enchantment, int level) {
            if (level <= 0) {
                this.traits.removeInt(enchantment);
            } else {
                this.traits.put(enchantment, Math.min(level, 255));
            }
        }

        public void upgrade(Holder<Trait> enchantment, int level) {
            if (level > 0) {
                this.traits.merge(enchantment, Math.min(level, 255), Integer::max);
            }
        }

        public void removeIf(Predicate<Holder<Trait>> predicate) {
            this.traits.keySet().removeIf(predicate);
        }

        public int getLevel(Holder<Enchantment> enchantment) {
            return this.traits.getOrDefault(enchantment, 0);
        }

        public Set<Holder<Trait>> keySet() {
            return this.traits.keySet();
        }

        public ItemTraits toImmutable() {
            return new ItemTraits(new Reference2IntArrayMap<>(this.traits));
        }
    }
}
