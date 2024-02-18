package dsm.pick2024.domain.application.port.out

import dsm.pick2024.domain.application.domain.Application
import java.util.UUID

interface FindApplicationByIdPort {
    fun findById(id: UUID): Application?
}
