package pl.piomin.samples.kubernetes.organization.domain

data class Department(var id: Int = 0,
                      val name: String = "",
                      val organizationId: Int = 0,
                      val employees: MutableSet<Employee> = mutableSetOf())