package pl.piomin.samples.kubernetes.employee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmployeeApp

fun main(args: Array<String>) {
    runApplication<EmployeeApp>(*args)
}