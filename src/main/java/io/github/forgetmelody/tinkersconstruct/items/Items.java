package io.github.forgetmelody.tinkersconstruct.items;

import io.github.forgetmelody.tinkersconstruct.TinkersConstruct;
import io.github.forgetmelody.tinkersconstruct.items.tools.ItemToolParts;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolDefinition;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolHelper;
import io.github.forgetmelody.tinkersconstruct.items.traits.ItemTraits;
import io.github.forgetmelody.tinkersconstruct.items.traits.TraitHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public final class Items {
    private static final DeferredRegister<Item> DEFERRED_REGISTER = DeferredRegister.create(Registries.ITEM, TinkersConstruct.MOD_ID);
    public static final Supplier<Item> TOOL = register(
            "tool",
            properties -> {
                properties.stacksTo(1);
                ToolHelper.toolDefinition(properties, ToolDefinition.EMPTY);
                ToolHelper.toolParts(properties, ItemToolParts.EMPTY);
                TraitHelper.traits(properties, ItemTraits.EMPTY);
                return properties;
            }
    );

    private Items() {
    }

    private static Supplier<Item> register(String name, UnaryOperator<Item.Properties> operator) {
        return DEFERRED_REGISTER.register(name, id -> new Item(operator.apply(new Item.Properties()).setId(ResourceKey.create(Registries.ITEM, id))));
    }

    public static void register(IEventBus modEventBus) {
        DEFERRED_REGISTER.register(modEventBus);
    }
}
