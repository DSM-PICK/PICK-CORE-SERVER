package dsm.pick2024.domain.classroom.port.out

import java.util.UUID

interface ClassroomDeletePort {
    fun deleteByUserId(userId: UUID)
}
