package pl.piomin.samples.kubernetes.controller;

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File

@RestController
@RequestMapping("/api")
class SampleUtilController {

    @GetMapping("/db-password")
    fun resourceString(): String {
        val file = File("/mnt/secrets-store/db-password")
        return if(file.exists()) file.readText()
        else "none"
    }
}
