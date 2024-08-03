package net.fabilucius.perfectify.tracking.plant.business.service

import net.fabilucius.perfectify.commons.exception.types.PlantNotAssignedToUser
import net.fabilucius.perfectify.commons.exception.types.PlantWateringMismatchException
import net.fabilucius.perfectify.commons.exception.types.WateringNotFoundById
import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.model.WateringBo
import net.fabilucius.perfectify.tracking.plant.business.model.toPlantEntity
import net.fabilucius.perfectify.tracking.plant.business.model.toWateringEntity
import net.fabilucius.perfectify.tracking.plant.persistance.entity.toWateringBo
import net.fabilucius.perfectify.tracking.plant.persistance.repository.WateringRepository
import net.fabilucius.perfectify.user.core.business.model.toUserEntity
import net.fabilucius.perfectify.user.core.business.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class WateringService(
    private val userService: UserService,
    private val plantService: PlantService,
    private val wateringRepository: WateringRepository
) {

    fun deleteWateringById(
        authenticatedUser: AuthenticatedUser,
        plantId: UUID,
        wateringId: UUID
    ): ResponseEntity<Unit> {
        val plant = plantService.getByIdAsUser(plantId, authenticatedUser)
        val watering = wateringRepository.findById(wateringId).orElseThrow { WateringNotFoundById(wateringId) }

        if (watering.plant.id != plant.id) {
            throw PlantWateringMismatchException(plantId, wateringId)
        }

        wateringRepository.delete(watering)
        return ResponseEntity.noContent().build()
    }

    fun getAllWateringsByPlantId(authenticatedUser: AuthenticatedUser, plantId: UUID): List<WateringBo> {
        val plant = plantService.getByIdAsUser(plantId, authenticatedUser)

        return wateringRepository.findAllByPlantId(plant.id!!).map { it.toWateringBo() }
    }

    fun createWatering(authenticatedUser: AuthenticatedUser, wateringBo: WateringBo): WateringBo {
        val plant = plantService.getByIdAsUser(wateringBo.plantId!!, authenticatedUser)
        val user = userService.getByFirebaseUid(authenticatedUser.firebaseUid)

        return wateringRepository.save(wateringBo.toWateringEntity(plant.toPlantEntity(user.toUserEntity())))
            .toWateringBo()
    }

}