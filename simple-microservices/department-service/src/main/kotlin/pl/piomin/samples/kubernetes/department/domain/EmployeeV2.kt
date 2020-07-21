package pl.piomin.samples.kubernetes.department.domain

class EmployeeV2(id: Int = 0,
                 val name: String = "",
                 position: String = ""): AbstractEmployee(id, position)