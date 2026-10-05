package qed.contract

/**
 * HTTP methods used in QED route definitions.
 *
 * Moved from qed.testbaseclass (QED-Shared) so it can be used from multiplatform code.
 */
enum class RequestType {
    GET,
    POST,
    PUT,
    DELETE,
    PATCH
}
