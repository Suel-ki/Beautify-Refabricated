package io.github.suel_ki.beautify.core.init;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.client.tooltip.BaseTooltipComponent;
import io.github.suel_ki.beautify.common.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ComponentInit {
    public static final DataComponentType<Blinds.TooltipComponent> BLINDS_TOOLTIP = register(
            "blinds_tooltip",
            Blinds.TooltipComponent.CODEC,
            Blinds.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<BookStack.TooltipComponent> BOOKSTACK_TOOLTIP = register(
            "bookstack_tooltip",
            BookStack.TooltipComponent.CODEC,
            BookStack.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<BotanistWorkbench.TooltipComponent> BOTANIST_WORKBENCH_TOOLTIP = register(
            "botanist_workbench_tooltip",
            BotanistWorkbench.TooltipComponent.CODEC,
            BotanistWorkbench.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<HangingPot.TooltipComponent> HANGING_POT_TOOLTIP = register(
            "hanging_pot_tooltip",
            HangingPot.TooltipComponent.CODEC,
            HangingPot.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<LampBamboo.TooltipComponent> LAMP_BAMBOO_TOOLTIP = register(
            "lamp_bamboo_tooltip",
            LampBamboo.TooltipComponent.CODEC,
            LampBamboo.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<LampCandelabra.TooltipComponent> LAMP_CANDELABRA_TOOLTIP = register(
            "lamp_candelabra_tooltip",
            LampCandelabra.TooltipComponent.CODEC,
            LampCandelabra.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<LampJar.TooltipComponent> LAMP_JAR_TOOLTIP = register(
            "lamp_jar_tooltip",
            LampJar.TooltipComponent.CODEC,
            LampJar.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<LampLightBulb.TooltipComponent> LAMP_LIGHTBULB_TOOLTIP = register(
            "lamp_lightbulb_tooltip",
            LampLightBulb.TooltipComponent.CODEC,
            LampLightBulb.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<PictureFrame.TooltipComponent> PICTURE_FRAME_TOOLTIP = register(
            "picture_frame_tooltip",
            PictureFrame.TooltipComponent.CODEC,
            PictureFrame.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<Rope.TooltipComponent> ROPE_TOOLTIP = register(
            "rope_tooltip",
            Rope.TooltipComponent.CODEC,
            Rope.TooltipComponent.STREAM_CODEC
    );

    public static final DataComponentType<Trellis.TooltipComponent> TRELLIS_TOOLTIP = register(
            "trellis_tooltip",
            Trellis.TooltipComponent.CODEC,
            Trellis.TooltipComponent.STREAM_CODEC
    );

    private static <T extends BaseTooltipComponent> DataComponentType<T> register(
            String id, Codec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec
    ) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                Beautify.id(id),
                DataComponentType.<T>builder()
                        .persistent(codec)
                        .networkSynchronized(streamCodec)
                        .build()
        );
    }

    public static void init() {}
}
