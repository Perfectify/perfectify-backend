package net.fabilucius.perfectify.user.core.business.service

import net.fabilucius.perfectify.commons.exception.types.EmailAlreadyTakenException
import net.fabilucius.perfectify.commons.exception.types.UserAlreadyRegisteredException
import net.fabilucius.perfectify.commons.exception.types.UserNotFoundByFirebaseUidException
import net.fabilucius.perfectify.user.core.business.model.UserBo
import net.fabilucius.perfectify.user.core.business.model.toUserEntity
import net.fabilucius.perfectify.user.core.persistance.entity.toUserBo
import net.fabilucius.perfectify.user.core.persistance.repository.UserRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(userBo: UserBo): UserBo {
        if (findByFirebaseUid(userBo.firebaseUid) != null) {
            throw UserAlreadyRegisteredException(userBo.firebaseUid)
        } else if (findByEmailIgnoreCase(userBo.email) != null) {
            throw EmailAlreadyTakenException(userBo.email)
        }
        return userRepository.save(userBo.toUserEntity()).toUserBo()
    }

    fun getByFirebaseUid(firebaseUid: String): UserBo =
        userRepository.findById(firebaseUid).orElseThrow { UserNotFoundByFirebaseUidException(firebaseUid) }.toUserBo()

    fun findByEmailIgnoreCase(email: String): UserBo? =
        userRepository.findByEmailIgnoreCase(email)?.toUserBo()

    fun findByFirebaseUid(firebaseUid: String): UserBo? =
        userRepository.findById(firebaseUid).getOrNull()?.toUserBo()

}