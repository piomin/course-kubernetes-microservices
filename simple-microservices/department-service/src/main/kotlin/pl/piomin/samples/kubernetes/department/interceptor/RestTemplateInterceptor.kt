package pl.piomin.samples.kubernetes.department.interceptor

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Component
import pl.piomin.samples.kubernetes.department.controller.DepartmentController
import pl.piomin.samples.kubernetes.department.service.AppVersion

@Component
class RestTemplateInterceptor(val appVersion: AppVersion): ClientHttpRequestInterceptor {

    val logger: Logger = LoggerFactory.getLogger(RestTemplateInterceptor::class.java)

    override fun intercept(req: HttpRequest,
                           body: ByteArray,
                           execution: ClientHttpRequestExecution): ClientHttpResponse {
        req.headers.add("X-Version", appVersion.getVersion())
        logger.info("Setting: {}", appVersion.getVersion())
        return execution.execute(req, body)
    }

}