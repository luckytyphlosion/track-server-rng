package luckytyphlosion.trackserverrng.mixin;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.function.BiFunction;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListener;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.WorldSaveHandler;
import net.minecraft.world.chunk.ChunkManager;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.level.LevelProperties;

import luckytyphlosion.trackserverrng.TraceRandom;

@Mixin(World.class)
public class WorldMixin {

    @Shadow @Mutable public Random random;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(final LevelProperties arg, final DimensionType arg2,
                      final BiFunction<World, Dimension, ChunkManager> biFunction,
                      final Profiler arg3, final boolean bl, CallbackInfo info) {
        if ((World)(Object)this instanceof ServerWorld) {
            this.random = new TraceRandom();            
        } else {
            this.random = new Random();
        }
    }
}
