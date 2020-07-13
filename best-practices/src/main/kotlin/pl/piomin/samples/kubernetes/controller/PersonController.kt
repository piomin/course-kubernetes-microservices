package pl.piomin.samples.kubernetes.controller

import org.springframework.web.bind.annotation.*
import pl.piomin.samples.kubernetes.domain.Person
import pl.piomin.samples.kubernetes.repository.PersonRepository
import java.util.*

@RestController
@RequestMapping("/persons")
class PersonController(private val repository: PersonRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<Person> = repository.findById(id)

    @GetMapping
    fun findAll(): Iterable<Person> = repository.findAll()

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int) = repository.deleteById(id)

    @PutMapping
    fun update(@RequestBody person: Person) = repository.save(person)

    @PostMapping
    fun add(@RequestBody person: Person) = repository.save(person)

}