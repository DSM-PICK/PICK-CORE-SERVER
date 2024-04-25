package dsm.pick2024.infrastructure.feign.client

import dsm.pick2024.infrastructure.feign.client.dto.request.DiscordMessageRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "discord-bug-stag-message-client", url = "\${discord.webhook.stag}")
interface DiscordStagClient {
    @PostMapping
    fun stagSendAlarm(@RequestBody message: DiscordMessageRequest)
}
