package net.fabilucius.perfectify.tracking.plant.controller

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.service.PlantService
import net.fabilucius.perfectify.tracking.plant.controller.request.PlantCreateRequest
import net.fabilucius.perfectify.tracking.plant.controller.request.toPlantBo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/plants")
class PlantController(
    private val plantService: PlantService
) {

    @GetMapping("/me")
    fun getPlantsFromAuthorization(authenticatedUser: AuthenticatedUser) =
        plantService.getAllByFirebaseUid(authenticatedUser.firebaseUid)

    @PostMapping
    fun postCreatePlant(@RequestBody plantCreateRequest: PlantCreateRequest, authenticatedUser: AuthenticatedUser) =
        plantService.createPlant(plantCreateRequest.toPlantBo(authenticatedUser))

}