package net.fabilucius.perfectify.status.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.status.controller.response.StatusResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/status")
class StatusController {

    @GetMapping
    fun getStatus(authenticatedUser: AuthenticatedUser): StatusResponse = StatusResponse(authenticatedUser)

}