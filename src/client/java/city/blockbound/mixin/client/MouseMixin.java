package city.blockbound.mixin.client;

import city.blockbound.ViewLockClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

	@Inject(at = @At("HEAD"), method = "lockCursor", cancellable = true)
	private void init(CallbackInfo info) {
		if (ViewLockClient.INSTANCE.isViewLocked()) {
			info.cancel();
		}
	}
}
