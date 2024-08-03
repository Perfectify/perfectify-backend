package net.fabilucius.perfectify.user.register.controller.request

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.user.core.business.model.UserBo

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
)

fun RegisterRequest.toUserBo(authenticatedUser: AuthenticatedUser) = UserBo(
    authenticatedUser.firebaseUid,
    authenticatedUser.email,
    authenticatedUser.signInProvider,
    this.firstName,
    this.lastName,
    null,
    null,
)
