package pl.piomin.samples.kubernetes.organization.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Organization(@Id @GeneratedValue var id: Int? = null,
                        val name: String = "",
                        @Transient val employees: MutableSet<Employee> = mutableSetOf(),
                        @Transient val departments: MutableSet<Department> = mutableSetOf())