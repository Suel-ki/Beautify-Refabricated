package io.github.suel_ki.beautify.common.block;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import it.unimi.dsi.fastutil.HashCommon;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

public class PictureFrame extends HorizontalDirectionalBlock implements BlockTooltip {
	private static final int MODELCOUNT = 13; // number of models the frame has
	public static final IntegerProperty FRAME_MOTIVE = IntegerProperty.create("frame_motive", 0, MODELCOUNT - 1);
	/** true when the frame stands turned 45 degrees past FACING, so it can face the 4 diagonal directions too */
	public static final BooleanProperty DIAGONAL = BooleanProperty.create("diagonal");
	protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 8, 11);

	public PictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState()
                .setValue(FRAME_MOTIVE, 0)
                .setValue(FACING, Direction.NORTH)
                .setValue(DIAGONAL, false));
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
		int randomNum = randomMotive(context);

		// 8-way placement: the frame turns to face the player, like signs and armour stands.
		// yaw 0 = south and increases clockwise, so 45 degree steps are 0=south, 1=south-west, ...
		Player player = context.getPlayer();
		float yaw = player != null ? player.getYRot() : context.getHorizontalDirection().getOpposite().toYRot();
		int step = Math.round(yaw / 45.0F) & 7;
		int front8 = (step + 4) & 7;      // the frame's front points back at the player
		int base = front8 - (front8 & 1); // even step: cardinal, odd step: 45 degrees past that cardinal
		Direction facing = Direction.fromYRot(base * 45.0);
		boolean diagonal = (front8 & 1) == 1;

		return this.defaultBlockState()
                .setValue(FACING, facing)
				.setValue(DIAGONAL, diagonal)
				.setValue(FRAME_MOTIVE, randomNum);
	}

	private static int randomMotive(BlockPlaceContext context) {
		long key = context.getClickedPos().asLong();
		Player player = context.getPlayer();
		if (player != null) {
			key ^= (long) player.getUUID().hashCode() << 32;
		}
		key ^= context.getHorizontalDirection().get2DDataValue();
		return Math.floorMod(HashCommon.murmurHash3(key), MODELCOUNT);
	}

	// creates blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FRAME_MOTIVE, FACING, DIAGONAL);
	}

    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.PICTURE_FRAME_TOOLTIP;
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.create(
                List.of(Component.translatable("tooltip.beautify.picture_frame.1"),
                        Component.translatable("tooltip.beautify.picture_frame.2")),
                List.of()
        );

        public static final Codec<TooltipLore> CODEC = TooltipLore.CODEC;
        public static final StreamCodec<RegistryFriendlyByteBuf, TooltipLore> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    }
}
