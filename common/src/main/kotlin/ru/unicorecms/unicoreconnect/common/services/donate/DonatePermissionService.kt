package ru.unicorecms.unicoreconnect.common.services.donate

import net.luckperms.api.LuckPerms
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.NodeType
import net.luckperms.api.node.types.PermissionNode
import ru.unicorecms.unicoreconnect.common.UnicoreCommon
import ru.unicorecms.unicoreconnect.common.types.DonatePermission
import ru.unicorecms.unicoreconnect.common.types.UserPermission
import java.util.*

class DonatePermissionService {
    private var config = UnicoreCommon.config
    private var baseUrl = "${config.apiUrl}/donates/permissions"

    companion object {
        var provider: LuckPerms? = null
        var permissions: Array<DonatePermission> = arrayOf()
    }

    fun load() {
        permissions = UnicoreCommon.requester.get("$baseUrl/server/uc/${config.server}").getOrThrow()
        provider = LuckPermsProvider.get()
    }

    fun handleJoin(uuid: UUID) {
        val user = provider!!.userManager.loadUser(uuid).get()
        val userPermissions = user.getNodes(NodeType.PERMISSION)
        val unicoreUserPermissions = UnicoreCommon.requester.get("$baseUrl/user/${config.server}/$uuid").getOrThrow<Array<UserPermission>>()

        unicoreUserPermissions.map { unicoreUserPerm ->
            if (unicoreUserPerm.permission.perms != null && unicoreUserPerm.permission.perms!!.isNotEmpty())
                if (unicoreUserPerm.permission.perms!!.filter { perm -> userPermissions.any { it.permission == perm }}.size != unicoreUserPerm.permission.perms!!.size )
                    add(unicoreUserPerm)
        }

        permissions.map { permission ->
            if (permission.perms != null && permission.perms!!.isNotEmpty())
                if (permission.perms!!.any { perm -> !unicoreUserPermissions.any { it.permission.perms?.contains(perm) == true } && userPermissions.any { it.permission == perm } })
                    remove(uuid, permission)
        }
    }

    fun add(userPermission: UserPermission) {
        val user = provider!!.userManager.loadUser(UUID.fromString(userPermission.user.uuid)).get()

        if (user != null && userPermission.permission.perms != null && userPermission.permission.perms!!.isNotEmpty()) {
            for (perm in userPermission.permission.perms!!) {
                val node = PermissionNode.builder(perm)

                if (userPermission.expired != null)
                    node.expiry(userPermission.expired!!.time / 1000)

                user.data().add(node.build())
            }

            provider!!.userManager.saveUser(user);
        }
    }

    fun remove(uuid: UUID, permission: DonatePermission) {
        val user = provider!!.userManager.loadUser(uuid).get()

        if (user != null && permission.perms != null && permission.perms!!.isNotEmpty()) {
            for (perm in permission.perms!!) {
                val node = user.getNodes(NodeType.PERMISSION).stream()
                    .filter { it.permission == perm }
                    .findFirst().get()

                user.data().remove(node)
            }

            provider!!.userManager.saveUser(user);
        }
    }
}