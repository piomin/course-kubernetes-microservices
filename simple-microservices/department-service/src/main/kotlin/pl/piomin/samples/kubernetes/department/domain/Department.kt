package pl.piomin.samples.kubernetes.department.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Department(@Id var id: Int, val name: String)