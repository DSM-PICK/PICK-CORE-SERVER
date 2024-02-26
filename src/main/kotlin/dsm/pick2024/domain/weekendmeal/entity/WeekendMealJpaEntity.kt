package dsm.pick2024.domain.weekendmeal.entity

import dsm.pick2024.domain.weekendmeal.enums.Status
import dsm.pick2024.global.base.BaseUUIDEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "tbl_weekend_meal")
class WeekendMealJpaEntity(
    id: UUID?,
    val userId: UUID,
    val username: String,
    val grade: Int,
    val classNum: Int,
    @Enumerated(value = EnumType.STRING)
    val status: Status
) : BaseUUIDEntity(id)
