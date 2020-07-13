package pl.piomin.samples.kubernetes.repository

import org.springframework.data.repository.CrudRepository
import pl.piomin.samples.kubernetes.domain.Person

interface PersonRepository: CrudRepository<Person, Int>