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
        var person = Instancio.of(Person::class.java)
            .ignore(Select.field("id"))
            .create()
        person = template.postForObject("/persons", person, Person::class.java)
        Assertions.assertNotNull(person)
        Assertions.assertNotNull(person.id)
        Assertions.assertEquals(1001, person.id)
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
        val person = template.getForObject("/persons/{id}", Person::class.java, 1)
        Assertions.assertNull(person)
    }

}