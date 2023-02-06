package pl.piomin.samples.kubernetes

import org.instancio.Instancio
import org.instancio.Select
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import pl.piomin.samples.kubernetes.domain.Person

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class PersonControllerTests {

    @Autowired
    lateinit var template: TestRestTemplate

    companion object {
        @Container
        val container = PostgreSQLContainer<Nothing>("postgres:14")

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", container::getJdbcUrl);
            registry.add("spring.datasource.password", container::getPassword);
            registry.add("spring.datasource.username", container::getUsername);
        }
    }

    @Test
    @Order(1)
    fun shouldAddPerson() {
        val person = Instancio.of(Person::class.java)
            .ignore(Select.field("id"))
            .create()
        val personAdd = template.postForObject("/persons", person, Person::class.java)
        Assertions.assertNotNull(personAdd)
        Assertions.assertNotNull(personAdd.id)
        println(personAdd)
    }

    @Test
    @Order(2)
    fun shouldFindAllPersons() {
        val persons = template.getForObject("/persons", List::class.java)
        Assertions.assertFalse(persons.isEmpty())
    }

    @Test
    @Order(2)
    fun shouldUpdatePerson() {
        val person = Instancio.of(Person::class.java)
            .set(Select.field("id"), 1)
            .create()
        template.put("/persons", person)
        val personRemote = template.getForObject("/persons/{id}", Person::class.java, 1)
        Assertions.assertNotNull(personRemote)
        Assertions.assertEquals(person.age, personRemote.age)
    }

    @Test
    @Order(3)
    fun shouldDeletePerson() {
        template.delete("/persons/{id}", 1)
        val person = template.getForEntity("/persons/{id}", String::class.java, 1)
        Assertions.assertTrue(person.statusCode.is2xxSuccessful)
    }

}