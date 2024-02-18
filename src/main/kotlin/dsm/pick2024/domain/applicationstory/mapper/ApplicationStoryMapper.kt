package dsm.pick2024.domain.applicationstory.mapper

import dsm.pick2024.domain.applicationstory.domain.ApplicationStory
import dsm.pick2024.domain.applicationstory.entity.ApplicationStoryJpaEntity
import org.springframework.stereotype.Component

@Component
class ApplicationStoryMapper {

    fun toEntity(domain: ApplicationStory) =
        domain.run {
            ApplicationStoryJpaEntity(
                id = id,
                reason = reason,
                startTime = startTime,
                endTime = endTime,
                username = username,
                date = date,
                type = type
            )
        }

    fun toDomain(entity: ApplicationStoryJpaEntity) =
        entity.run {
            ApplicationStory(
                id = id,
                reason = reason,
                startTime = startTime,
                endTime = endTime,
                username = username,
                date = date,
                type = type
            )
        }
}
