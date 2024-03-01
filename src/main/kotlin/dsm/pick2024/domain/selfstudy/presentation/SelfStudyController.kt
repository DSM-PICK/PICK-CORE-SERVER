package dsm.pick2024.domain.selfstudy.presentation

import dsm.pick2024.domain.selfstudy.port.`in`.QueryMonthSelfStudyTeacherUseCase
import dsm.pick2024.domain.selfstudy.port.`in`.QueryTodaySelfStudyTeacherUseCase
import dsm.pick2024.domain.selfstudy.port.`in`.RegistrationSelfStudyTeacherUseCase
import dsm.pick2024.domain.selfstudy.presentation.dto.request.RegistrationSelfStudyTeacherRequest
import dsm.pick2024.domain.selfstudy.service.ChangeSelfStudyTeacherService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.Month
import java.time.Year

@Tag(name = "selfStudy API")
@RestController
@RequestMapping("/self-study")
class SelfStudyController(
    private val registrationSelfStudyTeacherUseCase: RegistrationSelfStudyTeacherUseCase,
    private val changeSelfStudyTeacherService: ChangeSelfStudyTeacherService,
    private val queryTodaySelfStudyTeacherUseCase: QueryTodaySelfStudyTeacherUseCase,
    private val querySelfStudyTeacherUseCase: QueryMonthSelfStudyTeacherUseCase
) {

    @Operation(summary = "자습감독 선생님 등록 API")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/register")
    fun selfStudyTeacherRegister(
        @RequestBody registrationSelfStudyTeacherRequest: RegistrationSelfStudyTeacherRequest
    ) =
        registrationSelfStudyTeacherUseCase.registrationSelfStudyTeacher(registrationSelfStudyTeacherRequest)

    @Operation(summary = "자습감독 선생님 변경 API")
    @PatchMapping("/change")
    fun changeSelfStudyTeacher(
        @RequestBody changeSelfStudyTeacherRequest: RegistrationSelfStudyTeacherRequest
    ) =
        changeSelfStudyTeacherService.changeSelfStudyTeacher(changeSelfStudyTeacherRequest)

    @Operation(summary = "당일 자습감독 선생님 조회")
    @GetMapping("/today")
    fun queryTodaySelfStudyTeacher() =
        queryTodaySelfStudyTeacherUseCase.queryTodaySelfStudyTeacher()

    @Operation(summary = "한달 자습감독 선생님 조회")
    @GetMapping("/month")
    fun queryMonthSelfStudyTeacher(
        @RequestParam(name = "year")
        year: Year,
        @RequestParam(name = "month")
        month: Month
    ) =
        querySelfStudyTeacherUseCase.queryMonthSelfStudyTeacher(year, month)
}
