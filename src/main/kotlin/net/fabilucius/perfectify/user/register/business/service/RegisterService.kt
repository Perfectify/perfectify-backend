package net.fabilucius.perfectify.user.register.business.service

import net.fabilucius.perfectify.user.core.business.model.UserBo
import net.fabilucius.perfectify.user.core.business.service.UserService
import org.springframework.stereotype.Service

@Service
class RegisterService(
    private val userService: UserService
) {

    fun registerUser(userBo: UserBo): UserBo =
        userService.createUser(userBo)

}