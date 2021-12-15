package pl.piomin.samples.kubernetes.employee.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Employee(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
                    val firstName: String = "",
                    val lastName: String = "",
                    val position: String = "",
                    val organizationId: Int = 0,
                    val departmentId: Int = 0)