package uno.unicore.unicoreconnect.sponge;

import org.slf4j.Logger
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import javax.inject.Inject

@Suppress("unused")
@Plugin(
    id = "unicoreconnect",
    name = "UnicoreConnect",
    version = "@version@",
    description = "Plugin for integrating ZirconiaCMS with the Spigot and Sponge server platforms"
)
class UnicoreConnectPlugin {
    @Inject
    private lateinit var logger: Logger

    @Listener
    fun onServerStart() {
        logger.info("Successfully running ExamplePlugin!!!")
    }
}