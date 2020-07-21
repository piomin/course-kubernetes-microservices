package pl.piomin.samples.kubernetes.employee.domain.dto

class EmployeeV1DTO(id: Int? = null,
                    val firstName: String = "",
                    val lastName: String = "",
                    position: String = "",
                    organizationId: Int = 0,
                    departmentId: Int = 0):
        AbstractEmployeeDTO(id, position, organizationId, departmentId)