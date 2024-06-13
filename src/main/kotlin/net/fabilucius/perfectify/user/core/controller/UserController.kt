package net.fabilucius.perfectify.user.core.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.user.core.business.model.UserBo
import net.fabilucius.perfectify.user.core.business.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/me")
    fun getUserFromAuthorization(authenticatedUser: AuthenticatedUser): UserBo =
        userService.getByFirebaseUid(authenticatedUser.firebaseUid)

}