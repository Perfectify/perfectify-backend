package net.fabilucius.perfectify.commons.exception.types

import net.fabilucius.perfectify.commons.exception.PerfectifyException
import org.springframework.http.HttpStatus

class EmailAlreadyTakenException(email: String) :
    PerfectifyException(HttpStatus.CONFLICT, "The email $email is already taken by another user.")

class UserAlreadyRegisteredException(firebaseUid: String) :
    PerfectifyException(HttpStatus.CONFLICT, "A user with the firebaseUid $firebaseUid is already registered.")