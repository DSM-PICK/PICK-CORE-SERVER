package dsm.pick2024.domain.application.presentation

import dsm.pick2024.domain.application.port.`in`.CreateEarlyReturnUseCase
import dsm.pick2024.domain.application.port.`in`.StatusOKEarlyReturnUseCase
import dsm.pick2024.domain.application.presentation.dto.request.CreateEarlyReturnRequest
import dsm.pick2024.domain.application.presentation.dto.request.StatusEarlyReturnRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@Tag(name = "early API")
@RestController
@RequestMapping("/early-return")
class EarlyReturnController(
    private val createEarlyReturnUseCase: CreateEarlyReturnUseCase,
    private val statusEarlyReturnUseCase: StatusOKEarlyReturnUseCase
) {

    @Operation(summary = "조기귀가 신청 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    fun createEarlyReturn(
        @RequestBody createEarlyReturnRequest: CreateEarlyReturnRequest
    ) =
        createEarlyReturnUseCase.createEarlyReturn(createEarlyReturnRequest)

    @Operation(summary = "조기귀가 신청 상태변경 API")
    @PatchMapping("/status")
    fun statusEarlyReturn(
        @RequestBody statusEarlyReturnRequest: StatusEarlyReturnRequest
    ) =
        statusEarlyReturnUseCase.statusOKEarlyReturn(statusEarlyReturnRequest)
}
