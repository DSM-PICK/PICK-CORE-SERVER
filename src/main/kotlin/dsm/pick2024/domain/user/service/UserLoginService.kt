package dsm.pick2024.domain.user.service

import dsm.pick2024.domain.user.domain.User
import dsm.pick2024.domain.user.entity.enums.Role
import dsm.pick2024.domain.user.exception.PasswordMissMatchException
import dsm.pick2024.domain.user.exception.UserNotFoundException
import dsm.pick2024.domain.user.port.`in`.LoginUseCase
import dsm.pick2024.domain.user.port.out.ExistsByAccountIdPort
import dsm.pick2024.domain.user.port.out.FindByAccountIdPort
import dsm.pick2024.domain.user.port.out.UserSavePort
import dsm.pick2024.domain.user.presentation.dto.request.UserLoginRequest
import dsm.pick2024.global.security.jwt.JwtTokenProvider
import dsm.pick2024.global.security.jwt.dto.TokenResponse
import dsm.pick2024.infrastructure.feign.client.XquareFeignClient
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserLoginService(
    private val passwordEncoder: PasswordEncoder,
    private val findByAccountIdPort: FindByAccountIdPort,
    private val jwtTokenProvider: JwtTokenProvider,
    private val xquareFeignClient: XquareFeignClient,
    private val existsByAccountIdPort: ExistsByAccountIdPort,
    private val userSavePort: UserSavePort
) : LoginUseCase {

    @Transactional
    override fun login(userLoginRequest: UserLoginRequest): TokenResponse {

        val xquareUser = xquareFeignClient.xquareUser(userLoginRequest.accountId, userLoginRequest.password)

        if (existsByAccountIdPort.existsByAccountId(xquareUser.accountId) == false) {
            userSavePort.save(
                User(
                    id = xquareUser.id,
                    accountId = xquareUser.accountId,
                    password = passwordEncoder.encode(userLoginRequest.password),
                    name = xquareUser.name,
                    grade = xquareUser.grade,
                    classNum = xquareUser.classNum,
                    num = xquareUser.num,
                    birthDay = xquareUser.birthDay,
                    role = Role.STU
                )
            )
            return jwtTokenProvider.generateToken(xquareUser.name, Role.STU.toString())
        }

        val user = findByAccountIdPort.findByAccountId(userLoginRequest.accountId)
            ?: throw UserNotFoundException
        

        val token = jwtTokenProvider.generateToken(user.name, Role.STU.toString())
        return token
    }
}
