package net.fabilucius.perfectify.tracking.plant.persistance.repository

import net.fabilucius.perfectify.tracking.plant.persistance.entity.PlantEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.UUID

interface PlantRepository : JpaRepository<PlantEntity, UUID> {

    @Query(
        value = """
        SELECT plant FROM PlantEntity plant
        WHERE plant.user.firebaseUid = :firebaseUid
    """
    )
    fun findAllByFirebaseUid(firebaseUid: String): List<PlantEntity>

}