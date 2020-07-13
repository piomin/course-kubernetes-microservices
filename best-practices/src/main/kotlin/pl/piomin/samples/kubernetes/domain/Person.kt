package pl.piomin.samples.kubernetes.domain

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

@Entity
data class Person(@Id var id: Int?,
                  var name: String,
                  var age: Int,
                  @Enumerated(EnumType.ORDINAL) var gender: Gender)