package qed.contract

import qed.contract.auth.PermissionKey
import qed.contract.auth.PermissionRequirement
import qed.contract.routes.RouteDefinition
import qed.contract.routes.RouteGroupDefinition
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

// Minimal application-style enums, implemented the way Lukra and the listing app will.

private enum class TestPermission : PermissionKey { READ, WRITE, ADMIN }

private enum class TestGroup(
    override val baseRoute: String,
    override val requirement: PermissionRequirement
) : RouteGroupDefinition {
    PUBLIC("api/public", PermissionRequirement.None),
    ITEMS("/api/items/", PermissionRequirement.Single(TestPermission.READ)),  // deliberately untidy slashes
}

private enum class TestRoute(
    override val group: TestGroup,
    override val path: String,
    override val method: RequestType,
    override val permissionOverride: PermissionRequirement? = null,
    override val requiresAuth: Boolean = true,
    override val environments: List<Environment> = Environment.entries
) : RouteDefinition {
    PUBLIC_INFO(TestGroup.PUBLIC, "info", RequestType.GET, requiresAuth = false),
    ITEMS_LIST(TestGroup.ITEMS, "", RequestType.GET),
    ITEM_GET(TestGroup.ITEMS, "/{id}", RequestType.GET),
    ITEM_DELETE(TestGroup.ITEMS, "{id}", RequestType.DELETE,
        permissionOverride = PermissionRequirement.Single(TestPermission.ADMIN)),
}

class ContractTest {

    @Test
    fun fullPathJoinsWithSingleSlashes() {
        assertEquals("api/public/info", TestRoute.PUBLIC_INFO.fullPath)
        assertEquals("api/items/{id}", TestRoute.ITEM_GET.fullPath)
    }

    @Test
    fun emptyPathGivesBaseRoute() {
        assertEquals("api/items", TestRoute.ITEMS_LIST.fullPath)
    }

    @Test
    fun basePathHasOneLeadingSlash() {
        assertEquals("/api/items", TestGroup.ITEMS.basePath)
    }

    @Test
    fun requirementInheritsFromGroupUnlessOverridden() {
        assertEquals(PermissionRequirement.Single(TestPermission.READ), TestRoute.ITEM_GET.requirement)
        assertFalse(TestRoute.ITEM_GET.hasPermissionOverride)

        assertEquals(PermissionRequirement.Single(TestPermission.ADMIN), TestRoute.ITEM_DELETE.requirement)
        assertTrue(TestRoute.ITEM_DELETE.hasPermissionOverride)
    }

    @Test
    fun permissionChecks() {
        val user = setOf(TestPermission.READ)

        assertTrue(PermissionRequirement.None.check(user))
        assertTrue(PermissionRequirement.Single(TestPermission.READ).check(user))
        assertFalse(PermissionRequirement.Single(TestPermission.ADMIN).check(user))
        assertTrue(PermissionRequirement.AnyOf(setOf(TestPermission.READ, TestPermission.ADMIN)).check(user))
        assertFalse(PermissionRequirement.AllOf(setOf(TestPermission.READ, TestPermission.WRITE)).check(user))
    }

    @Test
    fun noneHasReadableName() {
        assertEquals("None", PermissionRequirement.None.toString())
    }
}
