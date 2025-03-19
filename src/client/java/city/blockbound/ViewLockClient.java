package city.blockbound;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ViewLockClient implements ClientModInitializer {

    public static ViewLockClient INSTANCE;

    private boolean viewLocked = false;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        final var toggleViewLock = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.toggleviewlock",
                        InputUtil.Type.KEYSYM,
                        GLFW.GLFW_KEY_V,
                        "key.category.viewlock"
                )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleViewLock.wasPressed()) {
                viewLocked = !viewLocked;

                if (!viewLocked) {
                    if (client.getOverlay() == null && client.currentScreen == null && !client.mouse.isCursorLocked()) {
                        client.mouse.lockCursor();
                    }
                } else {
                    client.mouse.unlockCursor();
                }
            }
        });
    }

    public boolean isViewLocked() {
        return viewLocked;
    }
}
