package net.fabilucius.perfectify.commons.exception.types

import net.fabilucius.perfectify.commons.exception.PerfectifyException
import org.springframework.http.HttpStatus
import java.util.UUID

class WateringNotFoundById(id: UUID) :
    PerfectifyException(HttpStatus.NOT_FOUND, "A watering with id $id was not found.")