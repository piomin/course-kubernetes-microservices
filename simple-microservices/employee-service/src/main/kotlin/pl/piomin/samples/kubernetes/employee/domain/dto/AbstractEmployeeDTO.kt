package pl.piomin.samples.kubernetes.employee.domain.dto

abstract class AbstractEmployeeDTO(var id: Int? = null,
                                   val position: String = "",
                                   val organizationId: Int = 0,
                                   val departmentId: Int = 0)