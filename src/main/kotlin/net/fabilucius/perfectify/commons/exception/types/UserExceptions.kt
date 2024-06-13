package net.fabilucius.perfectify.commons.exception.types

import net.fabilucius.perfectify.commons.exception.PerfectifyException
import org.springframework.http.HttpStatus

class UserNotFoundByFirebaseUidException(firebaseUid: String) :
    PerfectifyException(HttpStatus.NOT_FOUND, "No user with the firebaseUid $firebaseUid was found.")