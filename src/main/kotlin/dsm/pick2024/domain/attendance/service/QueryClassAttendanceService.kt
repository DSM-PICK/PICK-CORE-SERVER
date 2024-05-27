package dsm.pick2024.domain.attendance.service

import dsm.pick2024.domain.attendance.port.`in`.QueryClassAttendanceUseCase
import dsm.pick2024.domain.attendance.port.out.QueryClassAttendancePort
import dsm.pick2024.domain.attendance.presentation.dto.response.QueryAttendanceResponse
import dsm.pick2024.domain.classroom.port.out.ExistOKByUserIdPort
import dsm.pick2024.domain.classroom.port.out.FindOKClassroomPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryClassAttendanceService(
    private val queryClassAttendancePort: QueryClassAttendancePort,
    private val findOKClassroomPort: FindOKClassroomPort,
    private val existOKByUserIdPort: ExistOKByUserIdPort
) : QueryClassAttendanceUseCase {

    @Transactional(readOnly = true)
    override fun queryClassAttendance(
        grade: Int,
        classNum: Int
    ) =
        queryClassAttendancePort.findByGradeAndClassNum(grade, classNum)
        .map { it ->
            val userId = it.userId
            val classroomName =
                existOKByUserIdPort.existOKByUserId(userId)
                    .takeIf { it }
                    ?.let {
                        findOKClassroomPort.findOKClassroom(userId)?.classroomName
                    } ?: "" //예외처리 만들기

            with(it) {
                QueryAttendanceResponse(
                    id = userId,
                    username = name,
                    grade = grade,
                    classNum = classNum,
                    num = num,
                    status6 = period6,
                    status7 = period7,
                    status8 = period8,
                    status9 = period9,
                    status10 = period10,
                    classroomName = classroomName
                )
            }
        }
}
