package dsm.pick2024.domain.admin.entity

import dsm.pick2024.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_admin")
class AdminJpaEntity(
    id: UUID,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "class_rom")
    val classRoom: String? = null
): BaseUUIDEntity(id)
