package net.fabilucius.perfectify.commons.exception.types

import net.fabilucius.perfectify.commons.exception.PerfectifyException
import org.springframework.http.HttpStatus

open class InvalidWateringScheduleParameterException(message: String) :
    PerfectifyException(
        HttpStatus.BAD_REQUEST,
        message,
    )

class InvalidParameterPairException :
    InvalidWateringScheduleParameterException(
        "A watering schedule must at least consist of a pair of interval type and amount parameter or of a day of week and optionally a week of month parameter."
    )

class InvalidWeekOfMonthParameterException :
    InvalidWateringScheduleParameterException(
        "The week of month parameter has to be between 1 and 4."
    )