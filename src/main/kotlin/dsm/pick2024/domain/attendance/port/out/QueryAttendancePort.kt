package dsm.pick2024.domain.attendance.port.out

import dsm.pick2024.domain.attendance.domain.Attendance
import java.util.*

interface QueryAttendancePort {

    fun findAll(): List<Attendance>

    fun findByGradeAndClassNum(
        grade: Int,
        classNum: Int
    ): List<Attendance>

    fun findByClub(club: String): List<Attendance>

    fun findByUserId(userId: UUID): Attendance?
}
