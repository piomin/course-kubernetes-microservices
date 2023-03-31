package pl.piomin.samples.kubernetes.employee.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Employee(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int? = null,
                    val firstName: String = "",
                    val lastName: String = "",
                    val position: String = "",
                    val organizationId: Int = 0,
                    val departmentId: Int = 0)