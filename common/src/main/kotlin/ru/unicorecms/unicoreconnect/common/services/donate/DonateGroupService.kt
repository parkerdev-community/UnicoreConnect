package ru.unicorecms.unicoreconnect.common.services.donate

import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.context.DefaultContextKeys
import net.luckperms.api.node.NodeType
import net.luckperms.api.node.types.InheritanceNode
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.DonateGroup
import ru.unicorecms.unicoreconnect.common.types.UserDonate
import java.util.*

class DonateGroupService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/donates/groups"
    private var provider: LuckPerms? = null

    companion object {
        var groups: Array<DonateGroup> = arrayOf()
    }

    fun load() {
        groups = UnicoreCommon.requester.get("$baseUrl/server/${config.server}").getOrThrow()
        provider = LuckPermsProvider.get()
    }

    fun handleJoin(uuid: UUID) {
        val user = provider!!.userManager.loadUser(uuid).get()
        val userGroups = user.getInheritedGroups(user.queryOptions).filter {
            provider!!.serverName == "global" || it.queryOptions.context().contains(DefaultContextKeys.SERVER_KEY, provider!!.serverName)
        }
        val unicoreUserGroups = UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow<Array<UserDonate>>()

        unicoreUserGroups.map { unicoreUserGroup ->
            if (!userGroups.any { it.name == unicoreUserGroup.group.ingame_id })
                add(unicoreUserGroup)
        }

        groups.map { group ->
            if (userGroups.any { group.ingame_id == it.name } && !unicoreUserGroups.any { group.ingame_id == it.group.ingame_id })
                remove(uuid, group)
        }
    }

    fun add(userDonate: UserDonate) {
        val user = provider!!.userManager.loadUser(UUID.fromString(userDonate.user.uuid)).get()

        if (user != null) {
            val node = InheritanceNode.builder(userDonate.group.ingame_id)


            if (provider!!.serverName != "global")
                node.withContext(DefaultContextKeys.SERVER_KEY, provider!!.serverName)

            if (userDonate.expired != null)
                node.expiry(userDonate.expired!!.time / 1000)

            user.data().add(node.build())
            provider!!.userManager.saveUser(user);
        }
    }

    fun remove(uuid: UUID, userGroup: DonateGroup) {
        val user = provider!!.userManager.loadUser(uuid).get()

        if (user != null) {
            val node = user.getNodes(NodeType.INHERITANCE).firstOrNull {
                it.groupName == userGroup.ingame_id && (provider!!.serverName == "global" || it.contexts.contains(DefaultContextKeys.SERVER_KEY, provider!!.serverName))
            }

            if (node != null) {
                user.data().remove(node)
                provider!!.userManager.saveUser(user);
            }
        }
    }
}