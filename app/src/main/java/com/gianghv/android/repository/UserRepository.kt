package com.gianghv.android.repository

import com.gianghv.android.base.BaseRepository
import com.gianghv.android.domain.User
import com.gianghv.android.repository.source.UserDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {
    suspend fun login(email: String, password: String): Flow<User>
}

class UserRepositoryImpl @Inject constructor(private val remote: UserDataSource) : UserRepository, BaseRepository() {
    override suspend fun login(email: String, password: String) = flowContext {
        remote.login(email, password)
    }

}
