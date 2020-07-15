package pl.piomin.samples.kubernetes.organization.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Organization(@Id var id: Int, val name: String)