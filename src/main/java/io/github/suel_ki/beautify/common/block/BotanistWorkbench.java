package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.suel_ki.beautify.client.tooltip.BaseTooltipComponent;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Map;
import java.util.function.Consumer;

public class BotanistWorkbench extends HorizontalDirectionalBlock implements BlockTooltip<BotanistWorkbench.TooltipComponent> {
	//Map of hitboxes for direction the model can be facing
	private static final Map<Direction, VoxelShape> SHAPES_FOR_MODEL = ImmutableMap.of(
			Direction.NORTH, Shapes.or(box(2, 0, 0, 16, 12, 14.25),
					box(9.5, 12, 8.5, 13.5, 16, 12.5)),
			Direction.SOUTH, Shapes.or(box(0, 0, 1.75, 14, 12, 16),
					box(2.5, 12, 3.5, 6.5, 16, 7.5)),
			Direction.WEST, Shapes.or(box(0, 0, 0, 14.25, 12, 14),
					box(8.5, 12, 2.5, 12.5, 16, 6.5)),
			Direction.EAST, Shapes.or(box(1.75, 0, 2, 16, 12, 16),
					box(3.5, 12, 9.5, 7.5, 16, 13.5))
	);

	public static final MapCodec<BotanistWorkbench> CODEC = simpleCodec(BotanistWorkbench::new);

	public BotanistWorkbench(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<BotanistWorkbench> codec() {
		return CODEC;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPES_FOR_MODEL.get(state.getValue(FACING));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING);
	}

    @Override
    public DataComponentType<TooltipComponent> getTooltipType() {
        return ComponentInit.BOTANIST_WORKBENCH_TOOLTIP;
    }

    @Override
    public TooltipComponent getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent extends BaseTooltipComponent {
        public static final TooltipComponent INSTANCE = new TooltipComponent();

        private TooltipComponent() {}

        public static final Codec<TooltipComponent> CODEC = Codec.unit(TooltipComponent::new);
        public static final StreamCodec<RegistryFriendlyByteBuf, TooltipComponent> STREAM_CODEC = StreamCodec.unit(INSTANCE);

        @Override
        public void addShiftTooltips(Item.TooltipContext context, Consumer<Component> consumer, TooltipFlag flag, DataComponentGetter data) {
            consumer.accept(Component.translatable("tooltip.beautify.botanist_workbench.1")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
