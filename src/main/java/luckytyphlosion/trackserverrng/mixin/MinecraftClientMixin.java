package luckytyphlosion.trackserverrng.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import luckytyphlosion.trackserverrng.TraceRandom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerWorld;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Shadow private IntegratedServer server;

    @Inject(method = "disconnect(Lnet/minecraft/client/gui/screen/Screen;)V", at = @At("HEAD"))
    public void disconnect(Screen screen, CallbackInfo info) {
        if (this.server != null) {
             for (ServerWorld world : this.server.getWorlds()) {
                 ((TraceRandom)world.random).closePrintWriter();
             }
        }
    }
}
