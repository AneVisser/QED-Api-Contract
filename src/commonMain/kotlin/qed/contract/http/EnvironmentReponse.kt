package qed.contract.http

import kotlinx.serialization.Serializable

/**
 * Response of the public environment route (GET api/auth/environment) of the LUKRA backends.
 * The React frontends show it in the environment banner and the About dialog; the frontends'
 * server-status polling uses the route to detect that the backend is back.
 *
 * Moved here from Lukra's shared module (qed.testbaseclass.sut.dairymax_REST.models), with the
 * same fields, so both backends return the same shape. The handler that fills it lives in
 * LUKRA-Server-Core (nz.co.lukra.server.routing.SystemHandlers.environment).
 */
@Serializable
data class EnvironmentResponse(
    /** Name of qed.contract.Environment (DEVELOPMENT, STAGING, TEST, PRODUCTION), or UNKNOWN */
    val environment: String,
    /**
     * Always true since the app.security.enforce* settings were removed; kept (nullable, as in
     * Lukra) so the response shape doesn't change for existing clients and tests
     */
    val enforceAuthentication: Boolean?,
    /** Always true, see [enforceAuthentication] */
    val enforcePermissions: Boolean?,
    /** Lukra's beta mode (app.security.betaMode); false for apps without one */
    val betaMode: Boolean,
    /** app.version from the config (git commit at build time), or "unknown" */
    val version: String,
    /** app.buildDate from the config, or "unknown" */
    val buildDate: String
)
