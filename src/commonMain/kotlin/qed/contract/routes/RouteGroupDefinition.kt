package qed.contract.routes

import qed.contract.auth.PermissionRequirement

/**
 * A group of routes sharing a base route and a default permission requirement.
 *
 * Each application implements this with its own RouteGroup enum, e.g.:
 * ```
 * enum class RouteGroup(
 *     override val baseRoute: String,
 *     override val requirement: PermissionRequirement
 * ) : RouteGroupDefinition {
 *     ADMIN("api/admin", PermissionRequirement.Single(Permission.ADMIN)),
 * }
 * ```
 */
interface RouteGroupDefinition {
    /** Group name, used in generated documentation. Enums provide this automatically. */
    val name: String

    /** Base route, e.g. "api/admin". Leading/trailing slashes are tolerated. */
    val baseRoute: String

    /** Default permission requirement for all routes in this group. */
    val requirement: PermissionRequirement

    /** Base route with exactly one leading slash, e.g. "/api/admin". */
    val basePath: String
        get() = "/" + baseRoute.trim('/')
}
