package net.fabilucius.perfectify.commons.security

import com.google.firebase.auth.FirebaseToken

data class AuthenticatedUser(
    val firebaseUid: String,
    val email: String,
    val signInProvider: String,
)

fun FirebaseToken.toAuthenticatedUser() = AuthenticatedUser(
    this.uid,
    this.email,
    (this.claims["firebase"] as Map<*, *>)["sign_in_provider"] as String,
)
