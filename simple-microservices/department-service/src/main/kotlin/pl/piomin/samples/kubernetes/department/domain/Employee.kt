package pl.piomin.samples.kubernetes.department.domain

class Employee(id: Int = 0,
               val firstName: String = "",
               val lastName: String = "",
               position: String = ""): AbstractEmployee(id, position)