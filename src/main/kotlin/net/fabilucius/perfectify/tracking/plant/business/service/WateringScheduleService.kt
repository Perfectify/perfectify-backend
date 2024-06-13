package net.fabilucius.perfectify.tracking.plant.business.service

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.model.WateringScheduleBo
import net.fabilucius.perfectify.tracking.plant.business.model.toEntity
import net.fabilucius.perfectify.tracking.plant.business.model.toPlantEntity
import net.fabilucius.perfectify.tracking.plant.persistance.entity.toWateringScheduleBo
import net.fabilucius.perfectify.tracking.plant.persistance.repository.WateringScheduleRepository
import net.fabilucius.perfectify.user.core.business.model.toUserEntity
import net.fabilucius.perfectify.user.core.business.service.UserService
import org.springframework.stereotype.Service
import java.util.*

@Service
class WateringScheduleService(
    private val userService: UserService,
    private val plantService: PlantService,
    private val wateringScheduleRepository: WateringScheduleRepository
) {

    fun getAllWateringSchedulesByPlantId(
        authenticatedUser: AuthenticatedUser,
        plantId: UUID
    ): List<WateringScheduleBo> {
        val plant = plantService.getByIdAsUser(plantId, authenticatedUser)

        return wateringScheduleRepository.findAllByPlantId(plant.id!!).map { it.toWateringScheduleBo() }
    }

    fun createWatering(
        authenticatedUser: AuthenticatedUser,
        wateringScheduleBo: WateringScheduleBo
    ): WateringScheduleBo {
        val plant = plantService.getByIdAsUser(wateringScheduleBo.plantId!!, authenticatedUser)
        val user = userService.getByFirebaseUid(authenticatedUser.firebaseUid)

        return wateringScheduleRepository.save(wateringScheduleBo.toEntity(plant.toPlantEntity(user.toUserEntity())))
            .toWateringScheduleBo()
    }
}