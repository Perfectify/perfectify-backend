package net.fabilucius.perfectify.tracking.plant.controller.request

import net.fabilucius.perfectify.tracking.plant.business.model.WateringScheduleBo
import java.time.DayOfWeek
import java.time.temporal.ChronoUnit
import java.util.UUID

data class WateringScheduleCreateRequest(
    val intervalType: ChronoUnit?,
    val intervalAmount: Long?,
    val dayOfWeek: DayOfWeek?,
    val weekOfMonth: Int?,
)

fun WateringScheduleCreateRequest.toWateringScheduleBo(plantId: UUID) =
    WateringScheduleBo(
        null,
        plantId,
        this.intervalType,
        this.intervalAmount,
        this.dayOfWeek,
        this.weekOfMonth,
        null,
        null,
    )