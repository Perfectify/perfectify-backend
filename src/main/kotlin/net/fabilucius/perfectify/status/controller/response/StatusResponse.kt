package net.fabilucius.perfectify.status.controller.response

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import java.time.ZonedDateTime

data class StatusResponse(
    val authenticatedUser: AuthenticatedUser,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
)
