package pl.piomin.samples.kubernetes.employee.controller

import org.springframework.web.bind.annotation.*
import pl.piomin.samples.kubernetes.employee.domain.Employee
import pl.piomin.samples.kubernetes.employee.repository.EmployeeRepository
import java.util.*

@RestController
@RequestMapping("/employees")
class EmployeeController(val repository: EmployeeRepository) {

    @PostMapping
    fun add(@RequestBody employee: Employee): Employee = repository.save(employee)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<Employee> = repository.findById(id)

    @GetMapping
    fun findAll(): Iterable<Employee> = repository.findAll()

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int): Set<Employee> =
            repository.findByOrganizationId(organizationId)

    @GetMapping("/department/{departmentId}")
    fun findByDepartmentId(@PathVariable departmentId: Int): Set<Employee> =
            repository.findByDepartmentId(departmentId)
}