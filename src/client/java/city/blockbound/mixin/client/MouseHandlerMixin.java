package city.blockbound.mixin.client;

import city.blockbound.ViewLockClient;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

	@Inject(at = @At("HEAD"), method = "grabMouse", cancellable = true)
	private void init(CallbackInfo info) {
		if (ViewLockClient.INSTANCE.getViewLocked()) {
			info.cancel();
		}
	}
}
