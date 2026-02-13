package pl.piomin.samples.kubernetes.domain

import jakarta.persistence.*

@Entity
data class Person(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int?,
                  var name: String? = null,
                  var age: Int? = null,
                  @Enumerated(EnumType.ORDINAL) var gender: Gender? = null) {
    constructor() : this(null, "", null, Gender.MALE)
}