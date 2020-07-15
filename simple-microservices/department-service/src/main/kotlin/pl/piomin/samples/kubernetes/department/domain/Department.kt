package pl.piomin.samples.kubernetes.department.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Department(@Id var id: Int = 0,
                      val name: String = "",
                      val organizationId: Int = 0,
                      @Transient val employees: MutableSet<Employee> = mutableSetOf())