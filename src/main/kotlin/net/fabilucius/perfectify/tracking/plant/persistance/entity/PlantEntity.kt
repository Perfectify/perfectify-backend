package net.fabilucius.perfectify.tracking.plant.persistance.entity

import jakarta.persistence.*
import net.fabilucius.perfectify.tracking.plant.business.model.PlantBo
import net.fabilucius.perfectify.user.core.persistance.entity.UserEntity
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.ZonedDateTime
import java.util.*

@Entity
@Table(name = "plant")
data class PlantEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    var id: UUID?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity,

    @Column(name = "name")
    var name: String,

    @Column(name = "scientific_name")
    var scientificName: String,

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    var created: ZonedDateTime? = null,

    @Column(name = "modified", nullable = false)
    @UpdateTimestamp
    var modified: ZonedDateTime? = null,
)

fun PlantEntity.toPlantBo() = PlantBo(
    this.id,
    this.name,
    this.scientificName,
    this.user.firebaseUid,
    this.created,
    this.modified,
)