package pl.piomin.samples.kubernetes.department.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Department(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
                      val name: String = "",
                      val organizationId: Int = 0,
                      @Transient val employees: MutableSet<AbstractEmployee> = mutableSetOf())