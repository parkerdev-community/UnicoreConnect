package ru.zirconiamc.zirconiaconnect.sponge;

import org.slf4j.Logger
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GameStartedServerEvent
import org.spongepowered.api.plugin.Plugin
import javax.inject.Inject


@Plugin(id = "zirconiaconnect", name = "ZirconiaConnect", version = "@version@", description = "Example" )
public class ZirconiaConnect {
    @Inject
    private lateinit var logger: Logger

    @Listener
    fun onServerStart(event: GameStartedServerEvent?) {
        logger.info("Successfully running ExamplePlugin!!!")
    }
}