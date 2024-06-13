package net.fabilucius.perfectify.tracking.plant.business.model

import net.fabilucius.perfectify.tracking.plant.persistance.entity.PlantEntity
import net.fabilucius.perfectify.user.core.persistance.entity.UserEntity
import java.time.ZonedDateTime
import java.util.UUID

data class PlantBo(
    val id: UUID?,
    val name: String,
    val scientificName: String,
    val userId: String,
    val created: ZonedDateTime?,
    val modified: ZonedDateTime?,
)

fun PlantBo.toPlantEntity(userEntity: UserEntity) = PlantEntity(
    id = this.id,
    user = userEntity,
    name = this.name,
    scientificName = this.scientificName,
    modified = this.modified,
    created = this.created,
)
