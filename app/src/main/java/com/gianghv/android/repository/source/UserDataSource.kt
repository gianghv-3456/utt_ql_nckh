package com.gianghv.android.repository.source

import com.gianghv.android.base.BaseDataSource
import com.gianghv.android.base.DataResult
import com.gianghv.android.domain.User
import com.gianghv.android.repository.api.FirebaseApi
import javax.inject.Inject

interface UserDataSource {
    suspend fun login(email: String, password: String): DataResult<User>
}

class UserDataSourceImpl @Inject constructor(private val api: FirebaseApi) : UserDataSource, BaseDataSource() {
    override suspend fun login(email: String, password: String) = returnResult {
        api.login(email, password)
    }

}
