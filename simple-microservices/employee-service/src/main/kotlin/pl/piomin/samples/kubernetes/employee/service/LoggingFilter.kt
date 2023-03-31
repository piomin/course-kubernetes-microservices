package pl.piomin.samples.kubernetes.employee.service

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
class LoggingFilter: OncePerRequestFilter() {

    override fun doFilterInternal(req: HttpServletRequest,
                                  res: HttpServletResponse,
                                  chain: FilterChain) {
        val uuid = UUID.randomUUID().toString()
        MDC.put("X-Request-ID", uuid)
        val version = req.getHeader("X-Version")
        logger.info("X-Version: $version")

        logger.info("Request: method=${req.method}, uri=${req.requestURI}")
        chain.doFilter(req, res)
        logger.info("Response: status=${res.status}, uri=${req.requestURI}")
    }

}