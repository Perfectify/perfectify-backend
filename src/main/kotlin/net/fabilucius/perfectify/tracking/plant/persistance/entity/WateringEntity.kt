package net.fabilucius.perfectify.tracking.plant.persistance.entity

import jakarta.persistence.*
import net.fabilucius.perfectify.tracking.plant.business.model.WateringBo
import org.hibernate.annotations.CreationTimestamp
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "watering")
data class WateringEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    val id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    val plant: PlantEntity,

    @Column(name = "watered_at")
    val wateredAt: ZonedDateTime,

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    var created: ZonedDateTime? = null,
)

fun WateringEntity.toWateringBo() = WateringBo(
    this.id,
    this.plant.id,
    this.wateredAt,
    this.created,
)