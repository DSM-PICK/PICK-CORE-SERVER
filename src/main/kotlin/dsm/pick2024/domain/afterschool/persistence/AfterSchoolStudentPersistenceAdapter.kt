package dsm.pick2024.domain.afterschool.persistence

import dsm.pick2024.domain.afterschool.domain.AfterSchoolStudent
import dsm.pick2024.domain.afterschool.mapper.AfterSchoolStudentMapper
import dsm.pick2024.domain.afterschool.persistence.repository.AfterSchoolStudentRepository
import dsm.pick2024.domain.afterschool.port.out.AfterSchoolStudentPort
import org.springframework.stereotype.Component
import java.util.*

@Component
class AfterSchoolStudentPersistenceAdapter(
    private val afterSchoolStudentMapper: AfterSchoolStudentMapper,
    private val afterSchoolStudentRepository: AfterSchoolStudentRepository
): AfterSchoolStudentPort {

    override fun save(afterSchool: AfterSchoolStudent) {
        afterSchoolStudentRepository.save(afterSchoolStudentMapper.toEntity(afterSchool))
    }

    override fun deleteById(id: UUID) {
        afterSchoolStudentRepository.deleteById(id)
    }

    override fun findByAll() =
        afterSchoolStudentRepository.findAll().map { afterSchoolStudentMapper.toDomain(it) }
}
