package dsm.pick2024.domain.attendance.entity

import dsm.pick2024.domain.afterschool.enums.Status
import dsm.pick2024.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "tbl_attendance")
class AttendanceJpaEntity(
    id: UUID?,

    @Column(name = "user_id", nullable = false, columnDefinition = "BINARY(16)")
    val userId: UUID,

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(10)")
    val name: String,

    @Column(name = "grade", nullable = false, columnDefinition = "TINYINT(3)")
    val grade: Int,

    @Column(name = "class_num", nullable = false, columnDefinition = "TINYINT(4)")
    val classNum: Int,

    @Column(name = "num", nullable = false, columnDefinition = "TINYINT(20)")
    val num: Int,

    @Column(name = "club")
    val club: String? = null,

    @Column(name = "place")
    val place: String? = null,

    @Column(name = "floor")
    val floor: Int? = null,

    @Enumerated(value = EnumType.STRING)
    val period6: Status,

    @Enumerated(value = EnumType.STRING)
    val period7: Status,

    @Enumerated(value = EnumType.STRING)
    val period8: Status,

    @Enumerated(value = EnumType.STRING)
    val period9: Status,

    @Enumerated(value = EnumType.STRING)
    val period10: Status
) : BaseUUIDEntity(id)
