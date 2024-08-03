package net.fabilucius.perfectify.user.register.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.user.register.business.service.RegisterService
import net.fabilucius.perfectify.user.register.controller.request.RegisterRequest
import net.fabilucius.perfectify.user.register.controller.request.toUserBo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/register")
class RegisterController(
    private val registerService: RegisterService
) {

    @PostMapping
    fun postRegister(@RequestBody registerRequest: RegisterRequest, authenticatedUser: AuthenticatedUser) =
        registerService.registerUser(registerRequest.toUserBo(authenticatedUser))

}