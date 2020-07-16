package pl.piomin.samples.kubernetes.organization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import java.time.Duration

@SpringBootApplication
class OrganizationApp {

    @Bean
    fun restTemplate(): RestTemplate =
            RestTemplateBuilder()
                    .setReadTimeout(Duration.ofMillis(1000L))
                    .build()

}

fun main(args: Array<String>) {
    runApplication<OrganizationApp>(*args)
}