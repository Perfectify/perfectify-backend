package net.fabilucius.perfectify.tracking.plant.persistance.repository

import net.fabilucius.perfectify.tracking.plant.persistance.entity.WateringScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface WateringScheduleRepository : JpaRepository<WateringScheduleEntity, UUID> {

    @Query(
        value = """
        SELECT wateringSchedule FROM WateringScheduleEntity wateringSchedule
        WHERE wateringSchedule.plant.id = :plantId
        ORDER BY wateringSchedule.created DESC
    """
    )
    fun findAllByPlantId(plantId: UUID): List<WateringScheduleEntity>

}