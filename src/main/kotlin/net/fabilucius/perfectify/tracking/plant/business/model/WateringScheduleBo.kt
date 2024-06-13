package net.fabilucius.perfectify.tracking.plant.business.model

import net.fabilucius.perfectify.commons.exception.types.InvalidParameterPairException
import net.fabilucius.perfectify.commons.exception.types.InvalidWeekOfMonthParameterException
import net.fabilucius.perfectify.tracking.plant.persistance.entity.PlantEntity
import net.fabilucius.perfectify.tracking.plant.persistance.entity.WateringScheduleEntity
import java.time.DayOfWeek
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.UUID

data class WateringScheduleBo(
    val id: UUID?,
    val plantId: UUID?,
    val intervalType: ChronoUnit?,
    val intervalAmount: Long?,
    val dayOfWeek: DayOfWeek?,
    val weekOfMonth: Int?,
    val created: ZonedDateTime?,
    val modified: ZonedDateTime?,
) {
    init {
        require((intervalType != null && intervalAmount != null) || (dayOfWeek != null)) {
            throw InvalidParameterPairException()
        }
        require(weekOfMonth == null || weekOfMonth in 1..4) {
            throw InvalidWeekOfMonthParameterException()
        }
    }
}

fun WateringScheduleBo.toEntity(plantEntity: PlantEntity) =
    WateringScheduleEntity(
        this.id,
        plantEntity,
        this.intervalType,
        this.intervalAmount,
        this.dayOfWeek,
        this.weekOfMonth,
        this.created,
        this.modified
    )