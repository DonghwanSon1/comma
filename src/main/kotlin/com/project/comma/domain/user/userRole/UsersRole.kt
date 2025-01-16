package com.project.comma.domain.user.userRole

import com.project.comma.domain.user.users.Users
import javax.persistence.*

@Entity
@Table(name = "users_role")
class UsersRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sn")
    val sn: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "FK_USERS_ROLE_USER_SN"))
    val user: Users? = null,

    @Column(name = "role", nullable = false)
    val role: String? = null,

    ) {
}