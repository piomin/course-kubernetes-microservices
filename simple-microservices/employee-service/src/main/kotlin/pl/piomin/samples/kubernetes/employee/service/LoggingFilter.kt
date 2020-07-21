package pl.piomin.samples.kubernetes.employee.service

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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