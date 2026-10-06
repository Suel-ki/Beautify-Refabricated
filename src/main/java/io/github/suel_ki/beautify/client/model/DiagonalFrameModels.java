package io.github.suel_ki.beautify.client.model;

import com.mojang.math.Transformation;
import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

import java.util.Collection;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class DiagonalFrameModels implements ModelLoadingPlugin {
    private static final String DIAGONAL_SUFFIX = "_picture_frame_diagonal";
    private static final String DIAGONAL_BASE_PATH = "base_picture_frame_diagonal";
    private static final float YAW_DEGREES = -45.0F;

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        pluginContext.modifyModelBeforeBake().register(ModelModifier.WRAP_PHASE, (model, context) ->
                isDiagonalFrame(context.id()) ? new TurnedModel(model, YAW_DEGREES) : model);
    }

    // 1.20.1 element rotations are single axis, so the diagonal base is a copy of the cardinal one
    // and the 45 degree yaw is added here instead. The base itself is skipped: the children inherit
    // from it and would otherwise be turned twice.
    private static boolean isDiagonalFrame(ResourceLocation id) {
        if (id == null || !Beautify.MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath();
        return path.endsWith(DIAGONAL_SUFFIX) && !path.endsWith(DIAGONAL_BASE_PATH);
    }

    private record TurnedModel(UnbakedModel wrapped, float yaw) implements UnbakedModel {
        @Override
        public Collection<ResourceLocation> getDependencies() {
            return wrapped.getDependencies();
        }

        @Override
        public void resolveParents(Function<ResourceLocation, UnbakedModel> resolver) {
            wrapped.resolveParents(resolver);
        }

        @Override
        public BakedModel bake(ModelBaker baker,
                               Function<Material, TextureAtlasSprite> spriteGetter,
                               ModelState state,
                               ResourceLocation modelId) {
            return wrapped.bake(baker, spriteGetter, new TurnedState(state, yaw), modelId);
        }
    }

    private record TurnedState(ModelState delegate, float yaw) implements ModelState {
        @Override
        public Transformation getRotation() {
            // the bake space is centred on the block (blockstate rotations are pure rotations about
            // the origin), so this is a plain rotateY inside the blockstate rotation
            Matrix4f turn = new Matrix4f().rotateY((float) Math.toRadians(yaw));
            return new Transformation(new Matrix4f(delegate.getRotation().getMatrix()).mul(turn));
        }

        @Override
        public boolean isUvLocked() {
            return delegate.isUvLocked();
        }
    }
}
