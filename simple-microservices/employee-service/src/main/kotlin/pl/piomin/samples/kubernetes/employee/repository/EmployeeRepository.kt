package pl.piomin.samples.kubernetes.employee.repository

import org.springframework.data.repository.CrudRepository
import pl.piomin.samples.kubernetes.employee.domain.Employee

interface EmployeeRepository: CrudRepository<Employee, Int> {

    fun findByOrganizationId(organizationId: Int): Set<Employee>
    fun findByDepartmentId(departmentId: Int): Set<Employee>
}