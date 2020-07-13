package luckytyphlosion.trackserverrng.mixin;

import java.util.Random;
import java.util.concurrent.Executor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;

import luckytyphlosion.trackserverrng.TraceRandom;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Shadow public Random random;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(MinecraftServer minecraftServer, Executor executor, WorldSaveHandler arg2,
                      LevelProperties arg22, DimensionType arg32, Profiler arg42,
                      WorldGenerationProgressListener arg5, CallbackInfo info) {
        this.random = new TraceRandom();
    }
}
