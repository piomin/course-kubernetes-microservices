package pl.piomin.samples.kubernetes.organization.repository

import org.springframework.data.repository.CrudRepository
import pl.piomin.samples.kubernetes.organization.domain.Organization

interface OrganizationRepository: CrudRepository<Organization, Int> {
}