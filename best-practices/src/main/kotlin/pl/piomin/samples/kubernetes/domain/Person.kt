package pl.piomin.samples.kubernetes.domain

import jakarta.persistence.*

@Entity
data class Person(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int?,
                  var name: String,
                  var age: Int,
                  @Enumerated(EnumType.ORDINAL) var gender: Gender) {
    constructor() : this(null, "", 0, Gender.MALE)
}