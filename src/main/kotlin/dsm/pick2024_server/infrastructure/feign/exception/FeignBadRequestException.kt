package dsm.pick2024_server.infrastructure.feign.exception

import dsm.pick2024_server.global.error.exception.ErrorCode
import dsm.pick2024_server.global.error.exception.PickException

object FeignBadRequestException : PickException(
    ErrorCode.FEIGN_BAD_REQUEST
)
