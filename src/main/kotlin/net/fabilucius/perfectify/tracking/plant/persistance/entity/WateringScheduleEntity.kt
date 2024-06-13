package net.fabilucius.perfectify.tracking.plant.persistance.entity

import jakarta.persistence.*
import net.fabilucius.perfectify.tracking.plant.business.model.WateringScheduleBo
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.DayOfWeek
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

@Entity
@Table(name = "watering_schedule")
data class WateringScheduleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id", nullable = false)
    val plant: PlantEntity,

    @Column(name = "interval_type", nullable = true)
    val intervalType: ChronoUnit?,

    @Column(name = "interval_amount", nullable = true)
    val intervalAmount: Long?,

    @Column(name = "day_of_week", nullable = true)
    val dayOfWeek: DayOfWeek?,

    @Column(name = "week_of_month", nullable = true)
    val weekOfMonth: Int?,

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    var created: ZonedDateTime? = null,

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    var modified: ZonedDateTime? = null,
)

fun WateringScheduleEntity.toWateringScheduleBo() =
    WateringScheduleBo(
        this.id,
        this.plant.id,
        this.intervalType,
        this.intervalAmount,
        this.dayOfWeek,
        this.weekOfMonth,
        this.created,
        this.modified,
    )