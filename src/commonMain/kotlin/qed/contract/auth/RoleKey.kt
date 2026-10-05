package qed.contract.auth

/**
 * Identifies a role.
 *
 * Each application defines its own Role enum and implements this interface,
 * the same way as [PermissionKey]:
 * ```
 * enum class Role : RoleKey { USER, MANAGER, ADMIN }
 * ```
 */
interface RoleKey {
    val name: String
}
