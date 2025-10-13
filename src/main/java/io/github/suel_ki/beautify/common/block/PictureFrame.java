package io.github.suel_ki.beautify.common.block;

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
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Random;
import java.util.function.Consumer;

public class PictureFrame extends HorizontalDirectionalBlock implements BlockTooltip<PictureFrame.TooltipComponent> {
	private static final int MODELCOUNT = 13; // number of models the frame has
	public static final IntegerProperty FRAME_MOTIVE = IntegerProperty.create("frame_motive", 0, MODELCOUNT - 1);
	protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 8, 11);

	public static final MapCodec<PictureFrame> CODEC = simpleCodec(PictureFrame::new);

	public PictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FRAME_MOTIVE, 0).setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<PictureFrame> codec() {
		return CODEC;
	}

	// changing the model of the picture frame by shift-rightclicking
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
											BlockHitResult result) {
		if (!level.isClientSide() && player.isShiftKeyDown()) {
			int currentModel = state.getValue(FRAME_MOTIVE); // current index
			// reset if it surpasses the number of possible models
			if (currentModel + 1 > MODELCOUNT - 1) {
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, 0), 3);
			} else { // increases index
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, currentModel + 1), 3);
			}
			level.playSound(null, pos, SoundEvents.PAINTING_PLACE, SoundSource.BLOCKS, 1, 1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
			CollisionContext context) {
		return SHAPE;
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState blockState) {
		return Shapes.empty();
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Random rand = new Random();
		int randomNum = rand.nextInt((MODELCOUNT));

		return this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(FRAME_MOTIVE, randomNum);
	}

	// creates blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FRAME_MOTIVE, FACING);
	}

    @Override
    public DataComponentType<TooltipComponent> getTooltipType() {
        return ComponentInit.PICTURE_FRAME_TOOLTIP;
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
            consumer.accept(Component.translatable("tooltip.beautify.picture_frame.1")
                    .withStyle(ChatFormatting.GRAY));
            consumer.accept(Component.translatable("tooltip.beautify.picture_frame.2")
                    .withStyle(ChatFormatting.GRAY));
        }
    }
}
