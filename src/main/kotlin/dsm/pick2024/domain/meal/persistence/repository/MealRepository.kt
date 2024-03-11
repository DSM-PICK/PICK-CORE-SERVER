package dsm.pick2024.domain.meal.persistence.repository

import dsm.pick2024.domain.meal.domain.Meal
import dsm.pick2024.domain.meal.entity.MealJpaEntity
import java.time.LocalDate
import java.util.Date
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MealRepository : JpaRepository<MealJpaEntity, UUID> {
    fun findMealsByMealDate(date: LocalDate): List<Meal>?
}
