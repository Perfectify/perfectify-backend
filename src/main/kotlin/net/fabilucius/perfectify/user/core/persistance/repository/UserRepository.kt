package net.fabilucius.perfectify.user.core.persistance.repository

import net.fabilucius.perfectify.user.core.persistance.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, String> {

    fun findByEmailIgnoreCase(email: String): UserEntity?

}