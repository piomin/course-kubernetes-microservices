package pl.piomin.samples.kubernetes.organization.domain

class EmployeeV2(id: Int = 0,
                 val name: String = "",
                 position: String = ""): AbstractEmployee(id, position)