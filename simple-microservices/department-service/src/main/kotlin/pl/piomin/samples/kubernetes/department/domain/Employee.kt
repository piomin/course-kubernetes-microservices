package pl.piomin.samples.kubernetes.department.domain

data class Employee(var id: Int = 0,
                    val firstName: String = "",
                    val lastName: String = "",
                    val position: String = "")