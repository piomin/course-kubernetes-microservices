package pl.piomin.samples.kubernetes

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BestPracticesOnKubernetesApp

fun main(args: Array<String>) {
	runApplication<BestPracticesOnKubernetesApp>(*args)
}
