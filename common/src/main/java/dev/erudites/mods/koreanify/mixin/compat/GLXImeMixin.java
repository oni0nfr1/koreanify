package dev.erudites.mods.koreanify.mixin.compat;

import com.mojang.blaze3d.platform.GLX;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.LongSupplier;

@Mixin(GLX.class)
abstract class GLXImeMixin {
    @Inject(method = "_initGlfw", at = @At("HEAD"))
    private static void koreanify$enableX11OnTheSpotIme(
            CallbackInfoReturnable<LongSupplier> callbackInfo
    ) {
        String x11Display = System.getenv("DISPLAY");

        // GLFW_X11_ONTHESPOT affects only in GLFW X11 backend.
        // DISPLAY variable is set when X11/XWayland display is available.
        if (x11Display != null && !x11Display.isBlank()) {
            GLFW.glfwInitHint(GLFW.GLFW_X11_ONTHESPOT, GLFW.GLFW_TRUE);
        }
    }
}
