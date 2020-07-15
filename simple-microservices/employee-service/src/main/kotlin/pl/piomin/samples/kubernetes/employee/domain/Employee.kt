package pl.piomin.samples.kubernetes.employee.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Employee(@Id var id: Int = 0,
                    val firstName: String = "",
                    val lastName: String = "",
                    val position: String = "",
                    val organizationId: Int = 0,
                    val departmentId: Int = 0)