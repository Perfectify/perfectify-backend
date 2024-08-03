package net.fabilucius.perfectify.user.core.persistance.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import net.fabilucius.perfectify.user.core.business.model.UserBo
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime

@Entity
@Table(name = "user_account")
data class UserEntity(
    @Id
    @Column(name = "firebase_uid")
    var firebaseUid: String,

    @Column(name = "email", unique = true, nullable = false)
    var email: String,

    @Column(name = "sign_in_provider", nullable = false)
    var signInProvider: String,

    @Column(name = "first_name", nullable = false)
    var firstName: String,

    @Column(name = "last_name", nullable = false)
    var lastName: String,

    @UpdateTimestamp
    @Column(name = "modified", nullable = false)
    var modified: ZonedDateTime? = null,

    @CreationTimestamp
    @Column(name = "created", nullable = false, updatable = false)
    var created: ZonedDateTime? = null,
)

fun UserEntity.toUserBo() = UserBo(
    this.firebaseUid,
    this.email,
    this.signInProvider,
    this.firstName,
    this.lastName,
    this.modified!!,
    this.created!!
)