package pl.piomin.samples.kubernetes.department

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import pl.piomin.samples.kubernetes.department.interceptor.RestTemplateInterceptor
import java.time.Duration

@SpringBootApplication
class DepartmentApp {

    @Autowired
    lateinit var interceptor: RestTemplateInterceptor

    @Bean
    fun restTemplate(): RestTemplate =
            RestTemplateBuilder()
                    .interceptors(interceptor)
                    .setReadTimeout(Duration.ofMillis(1000L))
                    .build()
}

fun main(args: Array<String>) {
    runApplication<DepartmentApp>(*args)
}