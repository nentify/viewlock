package city.blockbound

import com.mojang.blaze3d.platform.InputConstants
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.KeyMapping
import org.lwjgl.glfw.GLFW

object ViewLockClient : ClientModInitializer {

    var viewLocked = false

    override fun onInitializeClient() {
        val toggleViewLock = KeyBindingHelper.registerKeyBinding(
            KeyMapping(
                "key.toggleviewlock",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "key.category.viewlock"
            )
        )

        ClientTickEvents.END_CLIENT_TICK.register { client ->
            while (toggleViewLock.consumeClick()) {
                viewLocked = !viewLocked

                if (!viewLocked) {
                    if (client.overlay == null && client.screen == null && !client.mouseHandler.isMouseGrabbed) {
                        client.mouseHandler.grabMouse()
                    }
                } else {
                    client.mouseHandler.releaseMouse()
                }
            }
        }
    }
}
