package qed.contract.auth

/**
 * Permission(s) a user needs to access a route.
 *
 * Moved from qed.testbaseclass.sut.dairymax_REST.routes (QED-Shared-DairyMax) and made
 * application-independent: it works with any [PermissionKey] instead of one Permission enum.
 */
sealed class PermissionRequirement {
    data class Single(val permission: PermissionKey) : PermissionRequirement()
    data class AnyOf(val permissions: Set<PermissionKey>) : PermissionRequirement()
    data class AllOf(val permissions: Set<PermissionKey>) : PermissionRequirement()

    // data object (instead of plain object) so toString() prints "None" in route documentation
    data object None : PermissionRequirement()

    fun check(userPermissions: Set<PermissionKey>): Boolean {
        return when (this) {
            is Single -> userPermissions.contains(permission)
            is AnyOf -> permissions.any { userPermissions.contains(it) }
            is AllOf -> permissions.all { userPermissions.contains(it) }
            is None -> true
        }
    }
}
