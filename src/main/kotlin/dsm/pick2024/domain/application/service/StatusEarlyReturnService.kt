package dsm.pick2024.domain.application.service

import dsm.pick2024.domain.admin.port.`in`.AdminFacadeUseCase
import dsm.pick2024.domain.application.enums.Status
import dsm.pick2024.domain.application.port.`in`.StatusEarlyReturnUseCase
import dsm.pick2024.domain.application.port.out.DeleteEarlyReturnApplicationPort
import dsm.pick2024.domain.application.port.out.FindEarlyReturnByIdPort
import dsm.pick2024.domain.application.port.out.SaveEarlyReturnPort
import dsm.pick2024.domain.application.presentation.dto.request.StatusEarlyReturnRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class StatusEarlyReturnService(
    private val adminFacadeUseCase: AdminFacadeUseCase,
    private val saveEarlyReturnPort: SaveEarlyReturnPort,
    private val findEarlyReturnByIdPort: FindEarlyReturnByIdPort,
    private val deleteEarlyReturnApplicationPort: DeleteEarlyReturnApplicationPort
) : StatusEarlyReturnUseCase {

    @Transactional
    override fun statusEarlyReturn(request: StatusEarlyReturnRequest, id: UUID) {
        val admin = adminFacadeUseCase.currentUser()

        if (Status.NO == request.status) {
            deleteEarlyReturnApplicationPort.deleteById(id)
        }

        val earlyReturn = findEarlyReturnByIdPort.findById(id)
        val update = earlyReturn.copy(
            teacherName = admin.name,
            status = request.status
        )

        saveEarlyReturnPort.save(update)
    }
}
