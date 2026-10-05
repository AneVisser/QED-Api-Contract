package qed.contract.auth

/**
 * Identifies a permission.
 *
 * Each application defines its own Permission enum and implements this interface.
 * Enums provide `name` automatically, so implementing it needs no extra code:
 * ```
 * enum class Permission : PermissionKey { ADMIN, EDIT_FARM }
 * ```
 */
interface PermissionKey {
    val name: String
}
