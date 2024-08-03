package net.fabilucius.perfectify.tracking.plant.business.model

import net.fabilucius.perfectify.tracking.plant.persistance.entity.PlantEntity
import net.fabilucius.perfectify.tracking.plant.persistance.entity.WateringEntity
import java.time.ZonedDateTime
import java.util.UUID

data class WateringBo(
    val id: UUID?,
    val plantId: UUID?,
    val wateredAt: ZonedDateTime,
    val created: ZonedDateTime?,
)

fun WateringBo.toWateringEntity(plantEntity: PlantEntity) = WateringEntity(
    this.id,
    plantEntity,
    this.wateredAt,
)
