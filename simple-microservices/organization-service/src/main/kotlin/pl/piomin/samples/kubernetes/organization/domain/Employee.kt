package pl.piomin.samples.kubernetes.organization.domain

data class Employee(var id: Int = 0,
                    val firstName: String = "",
                    val lastName: String = "",
                    val position: String = "")