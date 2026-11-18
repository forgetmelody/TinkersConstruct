package io.github.forgetmelody.tinkersconstruct.components;

import io.github.forgetmelody.tinkersconstruct.TinkersConstruct;
import io.github.forgetmelody.tinkersconstruct.items.tools.ItemToolParts;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolDefinition;
import io.github.forgetmelody.tinkersconstruct.items.traits.ItemTraits;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class DataComponents {
    private static final DeferredRegister<DataComponentType<?>> DEFERRED_REGISTER = DeferredRegister.create(
            Registries.DATA_COMPONENT_TYPE,
            TinkersConstruct.MOD_ID
    );
    public static final Supplier<DataComponentType<ToolDefinition>> TOOL_DEFINITION = register(
            "tool_definition",
            builder -> builder.persistent(ToolDefinition.CODEC).networkSynchronized(ToolDefinition.STREAM_CODEC)
    );
    public static final Supplier<DataComponentType<ItemToolParts>> TOOL_PARTS = register(
            "tool_parts",
            builder -> builder.persistent(ItemToolParts.CODEC).networkSynchronized(ItemToolParts.STREAM_CODEC)
    );
    public static final Supplier<DataComponentType<ItemTraits>> TRAITS = register(
            "traits",
            builder -> builder.persistent(ItemTraits.CODEC).networkSynchronized(ItemTraits.STREAMCODEC)
    );

    private DataComponents() {
    }

    private static <T> Supplier<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> operator) {
        return DEFERRED_REGISTER.register(name, () -> operator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus modEventBus) {
        DEFERRED_REGISTER.register(modEventBus);
    }
}
