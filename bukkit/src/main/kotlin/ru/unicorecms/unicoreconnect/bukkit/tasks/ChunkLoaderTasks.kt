package ru.unicorecms.unicoreconnect.bukkit.tasks

import ru.unicorecms.unicoreconnect.bukkit.PluginInstance

class ChunkLoaderTasks {
    private val plugin = PluginInstance.plugin

    fun handler() {
        val world = plugin.server.getWorld("test")
        val chunk = world!!.getChunkAt(1, 1)

        chunk.isForceLoaded = true
    }
}