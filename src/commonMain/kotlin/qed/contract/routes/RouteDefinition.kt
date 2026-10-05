package qed.contract.routes

import qed.contract.Environment
import qed.contract.RequestType
import qed.contract.auth.PermissionRequirement

/**
 * A single route: its group, relative path, HTTP method, permissions and environments.
 *
 * Each application implements this with its own Routes enum, e.g.:
 * ```
 * enum class Routes(
 *     override val group: RouteGroup,
 *     override val path: String,
 *     override val method: RequestType,
 *     override val permissionOverride: PermissionRequirement? = null,
 *     override val requiresAuth: Boolean = true,
 *     override val environments: List<Environment> = Environment.entries
 * ) : RouteDefinition {
 *     ADMIN_USERS_LIST(RouteGroup.ADMIN, "users", RequestType.GET),
 * }
 * ```
 */
interface RouteDefinition {
    /** Route name, used in generated documentation. Enums provide this automatically. */
    val name: String

    val group: RouteGroupDefinition

    /** Path relative to the group's base route, e.g. "users/{id}". May be empty. */
    val path: String

    val method: RequestType

    /** Overrides the group's default requirement when set. */
    val permissionOverride: PermissionRequirement?

    /** false for public routes (no JWT required). */
    val requiresAuth: Boolean

    /** Environments in which this route is registered. */
    val environments: List<Environment>

    /**
     * Full path including the group's base route, with slashes normalised:
     * no leading or trailing slash and no double slashes, e.g. "api/admin/users/{id}".
     * An empty [path] gives just the base route, e.g. "api/ingredients".
     */
    val fullPath: String
        get() = joinPath(group.baseRoute, path)

    /**
     * Effective permission requirement: [permissionOverride] if set,
     * otherwise the group's default.
     */
    val requirement: PermissionRequirement
        get() = permissionOverride ?: group.requirement

    /** true if this route overrides its group's permission requirement. */
    val hasPermissionOverride: Boolean
        get() = permissionOverride != null
}

/** Joins path parts with single slashes, ignoring empty parts and surplus slashes. */
internal fun joinPath(vararg parts: String): String =
    parts.map { it.trim('/') }
        .filter { it.isNotEmpty() }
        .joinToString("/")
