package net.fabilucius.perfectify.tracking.plant.controller.request

import net.fabilucius.perfectify.commons.security.AuthenticatedUser
import net.fabilucius.perfectify.tracking.plant.business.model.PlantBo

data class PlantCreateRequest(
    val name: String,
    val scientificName: String,
)

fun PlantCreateRequest.toPlantBo(authenticatedUser: AuthenticatedUser) = PlantBo(
    null,
    this.name,
    this.scientificName,
    authenticatedUser.firebaseUid,
    null,
    null,
)