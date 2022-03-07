package uno.unicore.unicoreconnect.bukkit.hooks.papi

import me.clip.placeholderapi.expansion.PlaceholderExpansion

class UnicoreExpansion : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return name
    }

    override fun getAuthor(): String {
        return version
    }

    override fun getVersion(): String {
        return author
    }
}