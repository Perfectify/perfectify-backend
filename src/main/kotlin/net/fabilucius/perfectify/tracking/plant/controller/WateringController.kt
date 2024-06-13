package net.fabilucius.perfectify.tracking.plant.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.service.WateringService
import net.fabilucius.perfectify.tracking.plant.controller.request.WateringCreateRequest
import net.fabilucius.perfectify.tracking.plant.controller.request.toWateringBo
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime
import java.util.*

@RestController
@RequestMapping("/plants/{plantId}/waterings")
class WateringController(
    private val wateringService: WateringService
) {

    @DeleteMapping("/{wateringId}")
    fun deleteWateringById(
        @PathVariable plantId: UUID,
        @PathVariable wateringId: UUID,
        authenticatedUser: AuthenticatedUser
    ) = wateringService.deleteWateringById(authenticatedUser, plantId, wateringId)

    @PostMapping
    fun postCreateWatering(
        @RequestBody wateringCreateRequest: WateringCreateRequest = WateringCreateRequest(ZonedDateTime.now()),
        @PathVariable plantId: UUID,
        authenticatedUser: AuthenticatedUser
    ) = wateringService.createWatering(authenticatedUser, wateringCreateRequest.toWateringBo(plantId))

    @GetMapping
    fun getWaterings(@PathVariable plantId: UUID, authenticatedUser: AuthenticatedUser) =
        wateringService.getAllWateringsByPlantId(authenticatedUser, plantId)

}