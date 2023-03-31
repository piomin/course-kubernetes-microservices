package pl.piomin.samples.kubernetes.employee

import org.instancio.Instancio
import org.instancio.Select
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import pl.piomin.samples.kubernetes.employee.domain.Employee
import pl.piomin.samples.kubernetes.employee.domain.dto.EmployeeV1DTO

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTests {

    @Autowired
    lateinit var template: TestRestTemplate

    @Test
    fun shouldAddEmployee() {
        var employee = Instancio.of(Employee::class.java)
            .ignore(Select.field("id"))
            .create()
        employee = template.postForObject("/employees", employee, Employee::class.java)
        assertNotNull(employee)
        assertNotNull(employee.id)
        assertEquals(9, employee.id)
    }

    @Test
    fun shouldGetEmployeeById() {
        val employee = template.getForObject("/employees/{id}", EmployeeV1DTO::class.java, 1)
        assertNotNull(employee)
        assertNotNull(employee.id)
        assertEquals(1, employee.id)
    }

    @Test
    fun shouldGetEmployeeByOrganizationId() {
        val employees = template.getForObject("/employees/organization/{organizationId}", Array<EmployeeV1DTO>::class.java, 1)
        assertNotNull(employees)
        assertTrue(employees.isNotEmpty())
    }
}