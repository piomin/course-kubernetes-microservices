package pl.piomin.samples.kubernetes.organization.controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import pl.piomin.samples.kubernetes.organization.domain.Department
import pl.piomin.samples.kubernetes.organization.domain.Employee
import pl.piomin.samples.kubernetes.organization.domain.Organization
import pl.piomin.samples.kubernetes.organization.repository.OrganizationRepository
import java.util.*

@RestController
@RequestMapping("/organizations")
class OrganizationController(val repository: OrganizationRepository,
                             val restTemplate: RestTemplate) {

    @PostMapping
    fun add(@RequestBody organization: Organization): Organization = repository.save(organization)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Optional<Organization> = repository.findById(id)

    @GetMapping("/{id}/with-employees")
    fun findByIdWithEmployees(@PathVariable id: Int): Organization? {
        val optOrganization: Optional<Organization> = repository.findById(id)
        return if (optOrganization.isPresent) {
            val organization: Organization = optOrganization.get()
            val employees = restTemplate.getForObject("http://employee-service:8080/employees/organization/{organizationId}",
                Array<Employee>::class.java, organization.id)
            organization.employees.addAll(employees!!)
            organization
        } else null
    }

    @GetMapping("/{id}/with-departments")
    fun findByIdWithDepartments(@PathVariable id: Int): Organization? {
        val optOrganization: Optional<Organization> = repository.findById(id)
        return if (optOrganization.isPresent) {
            val organization: Organization = optOrganization.get()
            val departments = restTemplate.getForObject("http://department-service:8080/departments/organization/{organizationId}",
                Array<Department>::class.java, organization.id)
            organization.departments.addAll(departments!!)
            organization
        } else null
    }

    @GetMapping()
    fun findAll(): Iterable<Organization> = repository.findAll()

}