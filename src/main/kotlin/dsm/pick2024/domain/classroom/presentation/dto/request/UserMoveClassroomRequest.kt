package dsm.pick2024.domain.classroom.presentation.dto.request

data class UserMoveClassroomRequest(
    val floor: Int,
    val classroomName: String,
    val start: Int,
    val end: Int
)
