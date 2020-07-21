package pl.piomin.samples.kubernetes.employee.domain.dto

class EmployeeV2DTO(id: Int? = null,
                    val name: String = "",
                    position: String = "",
                    organizationId: Int = 0,
                    departmentId: Int = 0):
        AbstractEmployeeDTO(id, position, organizationId, departmentId)