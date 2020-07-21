package pl.piomin.samples.kubernetes.department.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Department(@Id @GeneratedValue var id: Int? = null,
                      val name: String = "",
                      val organizationId: Int = 0,
                      @Transient val employees: MutableSet<AbstractEmployee> = mutableSetOf())