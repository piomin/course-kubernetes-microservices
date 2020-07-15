package pl.piomin.samples.kubernetes.department.controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import pl.piomin.samples.kubernetes.department.domain.Department
import pl.piomin.samples.kubernetes.department.domain.Employee
import pl.piomin.samples.kubernetes.department.repository.DepartmentRepository
import java.util.*

@RestController
@RequestMapping("/departments")
class DepartmentController(val repository: DepartmentRepository,
                           val restTemplate: RestTemplate) {

    @PostMapping
    fun add(@RequestBody department: Department): Department = repository.save(department)

    @PostMapping("/with-employees")
    fun addWithEmployees(@RequestBody department: Department): Department {
        val departmentSaved: Department = repository.save(department)
        department.employees.forEach {
            restTemplate.postForObject("http://employee-service:8080/employees",
                it, Employee::class.java)
        }
        return departmentSaved
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<Department> = repository.findById(id)

    @GetMapping("/{id}/with-employees")
    fun findByIdWithEmployees(@PathVariable id: Int): Department? {
        val optDepartment: Optional<Department> = repository.findById(id)
        return if (optDepartment.isPresent) {
            val department: Department = optDepartment.get()
            val employees = restTemplate.getForObject("http://employee-service:8080/employees/department/{departmentId}",
                    Array<Employee>::class.java, department.id)
            department.employees.addAll(employees!!)
            department
        } else null
    }

    @GetMapping()
    fun findAll(): Iterable<Department> = repository.findAll()

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int) =
            repository.findByOrganizationId(organizationId)
}