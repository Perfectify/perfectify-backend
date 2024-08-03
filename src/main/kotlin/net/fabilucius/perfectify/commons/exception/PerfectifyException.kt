package net.fabilucius.perfectify.commons.exception

import org.springframework.http.HttpStatus

abstract class PerfectifyException(
    val httpStatus: HttpStatus,
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)