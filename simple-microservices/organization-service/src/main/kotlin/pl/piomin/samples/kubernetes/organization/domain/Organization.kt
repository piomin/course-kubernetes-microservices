package pl.piomin.samples.kubernetes.organization.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Organization(@Id var id: Int,
                        val name: String,
                        @Transient val employees: MutableSet<Employee> = mutableSetOf(),
                        @Transient val departments: MutableSet<Department> = mutableSetOf())