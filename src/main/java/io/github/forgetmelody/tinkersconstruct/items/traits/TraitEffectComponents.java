package io.github.forgetmelody.tinkersconstruct.items.traits;

import com.mojang.serialization.Codec;
import io.github.forgetmelody.tinkersconstruct.TinkersConstruct;
import io.github.forgetmelody.tinkersconstruct.registries.BuiltInRegistries;
import io.github.forgetmelody.tinkersconstruct.registries.Registries;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class TraitEffectComponents {
    private static final DeferredRegister<DataComponentType<?>> DEFERRED_REGISTER = DeferredRegister.create(Registries.TRAIT_EFFECT_COMPONENT_TYPE, TinkersConstruct.MOD_ID);
    public static final Codec<DataComponentMap> CODEC = DataComponentMap.makeCodec(Codec.lazyInitialized(BuiltInRegistries.TRAIT_EFFECT_COMPONENT_TYPE::byNameCodec));

    private TraitEffectComponents() {
    }

    private static <T> Supplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> operator) {
        return DEFERRED_REGISTER.register(name, () -> operator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus modEventBus) {
        DEFERRED_REGISTER.register(modEventBus);
    }
}
