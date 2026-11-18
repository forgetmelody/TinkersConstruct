package io.github.forgetmelody.tinkersconstruct.items.traits;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public record TraitInstance(Holder<Trait> trait, int level) {
    public static final Codec<TraitInstance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Trait.REFERENCE_CODEC.fieldOf("traits").forGetter(TraitInstance::trait),
            Codec.INT.fieldOf("level").forGetter(TraitInstance::level)
    ).apply(instance, TraitInstance::new));
    public static final StreamCodec<FriendlyByteBuf, TraitInstance> STREAM_CODEC = StreamCodec.composite(

    );

    public static TraitInstance from(Reference2IntMap.Entry<Holder<Trait>> entry) {
        return new TraitInstance(entry.getKey(), entry.getIntValue());
    }
}
