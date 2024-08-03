package net.fabilucius.perfectify.tracking.plant.business.service

import net.fabilucius.perfectify.commons.exception.types.PlantNotAssignedToUser
import net.fabilucius.perfectify.commons.exception.types.PlantNotFoundById
import net.fabilucius.perfectify.commons.exception.types.UserNotFoundByFirebaseUidException
import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.model.PlantBo
import net.fabilucius.perfectify.tracking.plant.business.model.toPlantEntity
import net.fabilucius.perfectify.tracking.plant.persistance.entity.toPlantBo
import net.fabilucius.perfectify.tracking.plant.persistance.repository.PlantRepository
import net.fabilucius.perfectify.user.core.business.model.toUserEntity
import net.fabilucius.perfectify.user.core.business.service.UserService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlantService(
    private val plantRepository: PlantRepository,
    private val userService: UserService
) {

    fun createPlant(plantBo: PlantBo): PlantBo {
        val user = userService.findByFirebaseUid(plantBo.userId)
            ?: throw UserNotFoundByFirebaseUidException(plantBo.userId)

        return plantRepository.save(plantBo.toPlantEntity(user.toUserEntity())).toPlantBo()
    }

    fun getByIdAsUser(id: UUID, authenticatedUser: AuthenticatedUser) =
        getById(id).takeIf { it.userId == authenticatedUser.firebaseUid }
            ?: throw PlantNotAssignedToUser(id, authenticatedUser.firebaseUid)

    fun getById(id: UUID) =
        plantRepository.findById(id).orElseThrow { PlantNotFoundById(id) }.toPlantBo()

    fun getAllByFirebaseUid(firebaseUid: String) =
        plantRepository.findAllByFirebaseUid(firebaseUid).map { it.toPlantBo() }

}