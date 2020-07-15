package pl.piomin.samples.kubernetes.organization

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrganizationApp

fun main(args: Array<String>) {
    runApplication<OrganizationApp>(*args)
}