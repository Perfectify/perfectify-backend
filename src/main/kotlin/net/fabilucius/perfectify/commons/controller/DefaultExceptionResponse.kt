package net.fabilucius.perfectify.commons.controller

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

data class DefaultExceptionResponse(
    val message: String,
    val status: HttpStatus,
    val code: Int,
    val endpoint: String,
    val timestamp: ZonedDateTime = ZonedDateTime.now(),
)