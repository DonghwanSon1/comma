package com.project.comma.domain.user.users

import com.fasterxml.jackson.annotation.JsonIgnore
import com.project.comma.domain.user.users.rqrs.UserRq
import javax.persistence.*

@Entity
@Table(name = "users")
class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @Column(name = "email", unique = true, nullable = false)
    val email: String? = null,

    @JsonIgnore
    @Column(name = "password", nullable = false)
    val password: String? = null,

    @Column(name = "name", nullable = false)
    val name: String? = null,

    @Column(name = "phone", nullable = false)
    val phone: String? = null,

    @Column(name = "salary")
    val salary: Long? = null,

) {

    companion object {
        fun createUser(userRq: UserRq, encryptedPassword: String): Users {
            return Users(
                email = userRq.email,
                password = encryptedPassword,
                name = userRq.name,
                phone = userRq.phone,
                salary = userRq.salary
            )
        }

        fun from(sn: Long): Users {
            return Users(sn = sn)
        }
    }

    fun updateUser(userRq: UserRq, encryptedPassword: String?): Users {
        return Users(
            sn = this.sn,
            email = this.email,
            password = encryptedPassword ?: this.password,
            name = userRq.name ?: this.name,
            phone = userRq.phone ?: this.phone,
            salary = userRq.salary ?: this.salary
        )
    }
}