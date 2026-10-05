package qed.contract

/**
 * Deployment environments. Routes can be restricted to a subset of these
 * (e.g. test-only routes that must never be registered in PRODUCTION).
 *
 * Moved from qed.testbaseclass.sut.dairymax_REST.routes (QED-Shared-DairyMax).
 */
enum class Environment(val value: String) {
    DEVELOPMENT("development"),
    STAGING("staging"),
    TEST("test"),
    PRODUCTION("production")
}
