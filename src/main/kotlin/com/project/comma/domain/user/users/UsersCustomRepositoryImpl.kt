package com.project.comma.domain.user.users

import com.project.comma.domain.user.userRole.QUsersRole
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import com.project.comma.domain.user.users.rqrs.UserDto

@Repository
class UsersCustomRepositoryImpl(private val queryFactory: JPAQueryFactory) : UsersCustomRepository {

    private val users: QUsers = QUsers.users
    private val userRole: QUsersRole = QUsersRole.usersRole

    override fun findUserWithRoles(email: String): UserDto? {
        return queryFactory
            .select(
                Projections.fields(
                    UserDto::class.java,
                    users.sn,
                    users.email,
                    users.password,
                    users.name,
                    users.phone,
                    users.salary,
                    userRole.role
                )
            )
            .from(users)
            .join(userRole).on(userRole.user.sn.eq(users.sn))
            .where(users.email.eq(email))
            .fetchOne()
    }
}