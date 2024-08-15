package com.gianghv.android.views.common

import androidx.lifecycle.viewModelScope
import com.gianghv.android.base.BaseViewModel
import com.gianghv.android.domain.TokenModel
import com.gianghv.android.network.model.login.LoginRequest
import com.gianghv.android.network.model.signup.SignUpRequest
import com.gianghv.android.util.app.AppConstants
import com.gianghv.android.util.ext.Mapper.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
) : BaseViewModel() {

}
