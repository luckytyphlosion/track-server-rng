package luckytyphlosion.trackserverrng.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import luckytyphlosion.trackserverrng.TraceRandom;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "save", at = @At("HEAD"))
    public void save(boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<Boolean> info) {
        for (ServerWorld world : ((MinecraftServer)(Object)this).getWorlds()) {
            ((TraceRandom)world.random).flushPrintWriter();
        }
    }
}
