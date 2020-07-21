package pl.piomin.samples.kubernetes.employee.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import pl.piomin.samples.kubernetes.employee.domain.Employee
import pl.piomin.samples.kubernetes.employee.domain.dto.AbstractEmployeeDTO
import pl.piomin.samples.kubernetes.employee.domain.dto.EmployeeV1DTO
import pl.piomin.samples.kubernetes.employee.domain.dto.EmployeeV2DTO
import pl.piomin.samples.kubernetes.employee.repository.EmployeeRepository
import pl.piomin.samples.kubernetes.employee.service.AppVersion
import java.util.*

@RestController
@RequestMapping("/employees")
class EmployeeController(val repository: EmployeeRepository,
                         val appVersion: AppVersion) {

    val logger: Logger = LoggerFactory.getLogger(EmployeeController::class.java)

    @PostMapping
    fun add(@RequestBody employee: Employee): Employee = repository.save(employee)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<AbstractEmployeeDTO> = repository.findById(id)
            .map { mapEmployee(it) }

    @GetMapping
    fun findAll(): Iterable<AbstractEmployeeDTO> = repository.findAll().map { mapEmployee(it) }

    @GetMapping("/organization/{organizationId}")
    fun findByOrganizationId(@PathVariable organizationId: Int): List<AbstractEmployeeDTO> =
            repository.findByOrganizationId(organizationId).map { mapEmployee(it) }

    @GetMapping("/department/{departmentId}")
    fun findByDepartmentId(@PathVariable departmentId: Int): List<AbstractEmployeeDTO> =
            repository.findByDepartmentId(departmentId).map { mapEmployee(it) }

    fun mapEmployee(employee: Employee): AbstractEmployeeDTO {
        logger.info("Version: {}", appVersion.getVersion())
        return if (appVersion.getVersion()!!.contains("v1"))
            EmployeeV1DTO(employee.id, employee.firstName, employee.lastName, employee.position,
                    employee.organizationId, employee.departmentId)
        else
            EmployeeV2DTO(employee.id, "${employee.firstName} ${employee.lastName}", employee.position,
                    employee.organizationId, employee.departmentId)
    }

}