package pl.piomin.samples.kubernetes.department

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DepartmentApp

fun main(args: Array<String>) {
    runApplication<DepartmentApp>(*args)
}