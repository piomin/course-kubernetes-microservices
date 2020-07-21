package pl.piomin.samples.kubernetes.organization.service

import org.springframework.stereotype.Service
import java.io.File

@Service
class AppVersion {

    fun getVersion(): String? {
        val file = File("/etc/podinfo/labels")
        return if(file.exists()) {
            file.readLines().first{ it.startsWith("version=")}
                    .split("=")[1]
        } else null
    }
}