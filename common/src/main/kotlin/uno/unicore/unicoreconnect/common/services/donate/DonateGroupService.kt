package uno.unicore.unicoreconnect.common.services.donate

import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.types.InheritanceNode
import uno.unicore.unicoreconnect.common.UnicoreCommon
import uno.unicore.unicoreconnect.common.types.DonateGroup
import uno.unicore.unicoreconnect.common.types.UserDonate
import java.util.*


class DonateGroupService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/donates/groups"

    companion object {
        var provider: LuckPerms? = null
        var groups: List<DonateGroup> = listOf()
    }

    fun load() {
        groups = UnicoreCommon.requester.get("$baseUrl/server/${config.server}").getOrThrow()
        provider = LuckPermsProvider.get()
    }

    fun handleJoin(uuid: UUID) {
        val user = provider!!.userManager.loadUser(uuid).get()
        val userGroups = user.getInheritedGroups(user.queryOptions)
        val unicoreUserGroups = UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow<Array<UserDonate>>()

        unicoreUserGroups.map { unicoreUserGroup ->
            val userGroup = userGroups.find { it.name == unicoreUserGroup.group.ingame_id }

            if (userGroup == null) {
                val node = InheritanceNode.builder(unicoreUserGroup.group.ingame_id)

                if (unicoreUserGroup.expired != null)
                    node.expiry(unicoreUserGroup.expired!!.time / 1000)

                user.data().add(node.build())
                provider!!.userManager.saveUser(user);
            }
        }
    }

    fun add(userDonate: UserDonate) {
        val user = provider!!.userManager.loadUser(UUID.fromString(userDonate.user.uuid)).get()

        if (user != null) {
            val node = InheritanceNode.builder(userDonate.group.ingame_id)

            if (userDonate.expired != null)
                node.expiry(userDonate.expired!!.time / 1000)

            user.data().add(node.build())
            provider!!.userManager.saveUser(user);
        }
    }
}