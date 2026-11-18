package io.github.forgetmelody.tinkersconstruct.items.tools;

import io.github.forgetmelody.tinkersconstruct.components.DataComponents;
import net.minecraft.world.item.Item;

public final class ToolHelper {
    private ToolHelper() {
    }

    public static Item.Properties toolDefinition(Item.Properties properties, ToolDefinition toolDefinition) {
        return properties.component(DataComponents.TOOL_DEFINITION, toolDefinition);
    }

    public static Item.Properties toolParts(Item.Properties properties, ItemToolParts parts) {
        return properties.component(DataComponents.TOOL_PARTS, parts);
    }
}
