package pl.piomin.samples.kubernetes.organization.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Organization(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
                        val name: String = "",
                        @Transient val employees: MutableSet<Employee> = mutableSetOf(),
                        @Transient val departments: MutableSet<Department> = mutableSetOf())