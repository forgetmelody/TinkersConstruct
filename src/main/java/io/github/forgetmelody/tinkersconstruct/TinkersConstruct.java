package io.github.forgetmelody.tinkersconstruct;

import io.github.forgetmelody.tinkersconstruct.components.DataComponents;
import io.github.forgetmelody.tinkersconstruct.items.Items;
import io.github.forgetmelody.tinkersconstruct.items.tools.ToolEffectComponents;
import io.github.forgetmelody.tinkersconstruct.items.traits.Trait;
import io.github.forgetmelody.tinkersconstruct.items.traits.TraitEffectComponents;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(TinkersConstruct.MOD_ID)
public class TinkersConstruct {
    public static final String MOD_ID = "tinkers_construct";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TinkersConstruct(IEventBus modEventBus, ModContainer modContainer) {
        DataComponents.register(modEventBus);
        Items.register(modEventBus);
        ToolEffectComponents.register(modEventBus);
        TraitEffectComponents.register(modEventBus);
    }

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
