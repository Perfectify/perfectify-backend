package net.fabilucius.perfectify.user.core.business.model

import net.fabilucius.perfectify.user.core.persistance.entity.UserEntity
import java.time.ZonedDateTime

data class UserBo(
    val firebaseUid: String,
    val email: String,
    val signInProvider: String,
    val firstName: String,
    val lastName: String,
    val modified: ZonedDateTime?,
    val created: ZonedDateTime?,
)

fun UserBo.toUserEntity() = UserEntity(
    this.firebaseUid,
    this.email,
    this.signInProvider,
    this.firstName,
    this.lastName,
    this.modified,
    this.created,
)
