package net.fabilucius.perfectify.commons.exception

import jakarta.servlet.http.HttpServletRequest
import net.fabilucius.perfectify.commons.controller.DefaultExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(
        exception: Exception,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<DefaultExceptionResponse> {
        exception.printStackTrace()
        val status = (exception as? PerfectifyException)?.httpStatus ?: HttpStatus.INTERNAL_SERVER_ERROR
        val response = DefaultExceptionResponse(
            exception.message ?: "no exception message",
            status,
            status.value(),
            httpServletRequest.requestURI
        )
        return ResponseEntity(response, status)
    }

}