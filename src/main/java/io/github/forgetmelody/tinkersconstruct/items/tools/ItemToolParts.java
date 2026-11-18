package io.github.forgetmelody.tinkersconstruct.items.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.forgetmelody.tinkersconstruct.items.materials.Material;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceArrayMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceMaps;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.util.Map;
import java.util.Objects;

public final class ItemToolParts {
    private final Reference2ReferenceMap<Holder<ToolPart>, Holder<Material>> parts;
    public static final Codec<ItemToolParts> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.unboundedMap(ToolPart.REFERENCE_CODEC, Material.REFERENCE_CODEC).fieldOf("parts").forGetter(ItemToolParts::parts)
    ).apply(instance, ItemToolParts::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemToolParts> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Reference2ReferenceArrayMap::new, ToolPart.STREAM_CODEC, Material.STREAM_CODEC), ItemToolParts::parts,
            ItemToolParts::new
    );
    public static final ItemToolParts EMPTY = new ItemToolParts(Reference2ReferenceMaps.emptyMap());

    private ItemToolParts(Map<Holder<ToolPart>, Holder<Material>> parts) {
        this.parts = new Reference2ReferenceArrayMap<>(parts);
    }

    private ItemToolParts(Reference2ReferenceMap<Holder<ToolPart>, Holder<Material>> parts) {
        this.parts = parts;
    }

    private Reference2ReferenceMap<Holder<ToolPart>, Holder<Material>> parts() {
        return parts;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ItemToolParts) obj;
        return Objects.equals(this.parts, that.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parts);
    }

    @Override
    public String toString() {
        return "ItemToolParts[" +
                "parts=" + parts + ']';
    }

    public static class Mutable {
        private final Reference2ReferenceMap<Holder<ToolPart>, Holder<Material>> parts;

        private Mutable(Reference2ReferenceMap<Holder<ToolPart>, Holder<Material>> parts) {
            this.parts = parts;
        }

        public ItemToolParts toImmutable() {
            return new ItemToolParts(new Reference2ReferenceArrayMap<>(this.parts));
        }
    }
}
