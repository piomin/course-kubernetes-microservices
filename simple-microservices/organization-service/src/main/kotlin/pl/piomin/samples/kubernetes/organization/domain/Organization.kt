package pl.piomin.samples.kubernetes.organization.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Organization(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
                        val name: String = "",
                        @Transient val employees: MutableSet<Employee> = mutableSetOf(),
                        @Transient val departments: MutableSet<Department> = mutableSetOf())