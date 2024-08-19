package com.gianghv.android.views.common

import androidx.lifecycle.MutableLiveData
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.UserRole
import com.gianghv.android.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {
    private val _userRole = MutableLiveData<UserRole?>(null)
    val userRole: MutableLiveData<UserRole?> = _userRole

    fun signIn(userName: String, password: String) {
        runFlow(Dispatchers.IO) {
            userRepository.login(userName, password).collect{
                Timber.d("collect $it")
                AppState.userId = it.id
                _userRole.postValue(it.role)
            }
        }
    }
}
