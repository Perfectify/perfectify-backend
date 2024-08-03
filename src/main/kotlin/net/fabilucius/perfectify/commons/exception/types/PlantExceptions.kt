package net.fabilucius.perfectify.commons.exception.types

import net.fabilucius.perfectify.commons.exception.PerfectifyException
import org.springframework.http.HttpStatus
import java.util.UUID

class PlantNotFoundById(id: UUID) :
    PerfectifyException(HttpStatus.NOT_FOUND, "A plant with id $id was not found.")

class PlantNotAssignedToUser(id: UUID, userId: String) :
    PerfectifyException(
        HttpStatus.UNAUTHORIZED,
        "The user with id $userId is not assigned to the plant with the id $id."
    )

class PlantWateringMismatchException(plantId: UUID, wateringId: UUID) :
    PerfectifyException(
        HttpStatus.UNPROCESSABLE_ENTITY,
        "The watering with id $wateringId does not belong to the plant with id $plantId."
    )