package qed.contract.http

import kotlinx.serialization.Serializable

// Error body of every QED backend: {"error": "..."}.
// Moved from LUKRA-Server-Core so apps and test suites can read it too.
@Serializable
data class ErrorResponse(val error: String)