package pl.piomin.samples.kubernetes.department.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import pl.piomin.samples.kubernetes.department.domain.AbstractEmployee
import pl.piomin.samples.kubernetes.department.domain.Department
import pl.piomin.samples.kubernetes.department.domain.Employee
import pl.piomin.samples.kubernetes.department.domain.EmployeeV2
import pl.piomin.samples.kubernetes.department.repository.DepartmentRepository
import pl.piomin.samples.kubernetes.department.service.AppVersion
import java.util.*

@RestController
@RequestMapping("/departments")
class DepartmentController(val repository: DepartmentRepository,
                           val restTemplate: RestTemplate,
                           val appVersion: AppVersion) {

    val logger: Logger = LoggerFactory.getLogger(DepartmentController::class.java)

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
//            val employees = restTemplate.getForObject("http://employee-service:8080/employees/department/{departmentId}",
//                    Array<Employee>::class.java, department.id)
            department.employees.addAll(getEmployeesByDepartmentId(department.id!!)!!)
            department
        } else null
    }

    @GetMapping()
    fun findAll(): Iterable<Department> = repository.findAll()

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int) =
            repository.findByOrganizationId(organizationId)

    fun getEmployeesByDepartmentId(departmentId: Int): Array<out AbstractEmployee>? {
        logger.info("Version: {}", appVersion.getVersion())
        return if (appVersion.getVersion()!!.contains("v1")) {
            restTemplate.getForObject("http://employee-service:8080/employees/department/{departmentId}",
                    Array<Employee>::class.java, departmentId)
        } else {
            restTemplate.getForObject("http://employee-service:8080/employees/department/{departmentId}",
                    Array<EmployeeV2>::class.java, departmentId)
        }
    }

}