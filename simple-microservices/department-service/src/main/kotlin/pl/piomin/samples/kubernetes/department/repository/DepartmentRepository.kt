package pl.piomin.samples.kubernetes.department.repository

import org.springframework.data.repository.CrudRepository
import pl.piomin.samples.kubernetes.department.domain.Department

interface DepartmentRepository: CrudRepository<Department, Int> {

    fun findByOrganizationId(organizationId: Int)

}