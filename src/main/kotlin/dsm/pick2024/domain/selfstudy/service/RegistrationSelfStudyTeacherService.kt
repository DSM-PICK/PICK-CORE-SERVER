package dsm.pick2024.domain.selfstudy.service

import dsm.pick2024.domain.selfstudy.domain.SelfStudy
import dsm.pick2024.domain.selfstudy.port.`in`.RegistrationSelfStudyTeacherUseCase
import dsm.pick2024.domain.selfstudy.port.out.FindByDatePort
import dsm.pick2024.domain.selfstudy.port.out.SelfStudySaveAllPort
import dsm.pick2024.domain.selfstudy.presentation.dto.request.RegistrationSelfStudyTeacherRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RegistrationSelfStudyTeacherService(
    private val selfStudySaveAllPort: SelfStudySaveAllPort,
    private val findByDatePort: FindByDatePort
) : RegistrationSelfStudyTeacherUseCase {

    @Transactional
    override fun registrationSelfStudyTeacher(request: RegistrationSelfStudyTeacherRequest) {
        val teacher = request.teacher.map { teacherRequest ->
            findByDatePort.findByDate(request.date)?.let { findSelfStudy ->

                findSelfStudy.apply {
                    teacher = teacherRequest.teacher
                }
            } ?: SelfStudy(
                date = request.date,
                floor = teacherRequest.floor,
                teacher = teacherRequest.teacher
            )
        }

        selfStudySaveAllPort.saveAll(teacher)
    }
}
