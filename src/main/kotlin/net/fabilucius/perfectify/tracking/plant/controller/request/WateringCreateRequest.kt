package net.fabilucius.perfectify.tracking.plant.controller.request

import net.fabilucius.perfectify.tracking.plant.business.model.WateringBo
import java.time.ZonedDateTime
import java.util.UUID

data class WateringCreateRequest(
    val wateredAt: ZonedDateTime,
)

fun WateringCreateRequest.toWateringBo(plantId: UUID) = WateringBo(
    null,
    plantId,
    this.wateredAt,
    null,
)