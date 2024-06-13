package net.fabilucius.perfectify.tracking.plant.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.service.WateringScheduleService
import net.fabilucius.perfectify.tracking.plant.controller.request.WateringScheduleCreateRequest
import net.fabilucius.perfectify.tracking.plant.controller.request.toWateringScheduleBo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/plants/{plantId}/watering-schedules")
class WateringScheduleController(
    private val wateringScheduleService: WateringScheduleService
) {

    @GetMapping
    fun getAllWateringSchedules(
        @PathVariable plantId: UUID,
        authenticatedUser: AuthenticatedUser
    ) = wateringScheduleService.getAllWateringSchedulesByPlantId(authenticatedUser, plantId)

    @PostMapping
    fun postCreateWateringSchedule(
        @PathVariable plantId: UUID,
        @RequestBody wateringScheduleCreateRequest: WateringScheduleCreateRequest,
        authenticatedUser: AuthenticatedUser
    ) = wateringScheduleService.createWatering(
        authenticatedUser,
        wateringScheduleCreateRequest.toWateringScheduleBo(plantId)
    )

}