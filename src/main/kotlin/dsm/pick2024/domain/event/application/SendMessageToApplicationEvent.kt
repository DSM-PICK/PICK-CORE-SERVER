package dsm.pick2024.domain.event.application

import dsm.pick2024.domain.application.domain.Application
import dsm.pick2024.domain.application.enums.ApplicationKind
import dsm.pick2024.domain.application.enums.Status
import dsm.pick2024.domain.event.Topic
import dsm.pick2024.domain.notification.port.out.CommendTopicSubscriptionPort
import dsm.pick2024.domain.notification.presentation.dto.request.Notification
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class SendMessageToApplicationEvent(
    private val commendTopicSubscriptionPort: CommendTopicSubscriptionPort
): SendMessageToApplicationEventPort {

    @Async
    override fun send(
        deviceToken: String, topic: Topic,
        status: Status, applicationKind: ApplicationKind,
        application: Application?
    ) {
        commendTopicSubscriptionPort.sendMessage(
            deviceToken,
            Notification(
                topic = Topic.APPLICATION,
                title = "${if (applicationKind.name == "application") "외출" else "조기귀가"} 신청이 " +
                    "${if (status == Status.OK) "수락" else "거절"}되었습니다.",
                body = if (status == Status.OK) {
                    "${application?.start}부터 이용 가능합니다."
                } else {
                    "신청이 거절되었습니다."
                }
            )
        )
    }
}
