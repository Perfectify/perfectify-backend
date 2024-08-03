package net.fabilucius.perfectify.tracking.plant.persistance.repository

import net.fabilucius.perfectify.tracking.plant.persistance.entity.WateringEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface WateringRepository : JpaRepository<WateringEntity, UUID> {

    @Query(
        value = """
        SELECT watering FROM WateringEntity watering
        WHERE watering.plant.id = :plantId
        ORDER BY watering.wateredAt DESC
    """
    )
    fun findAllByPlantId(plantId: UUID): List<WateringEntity>

}