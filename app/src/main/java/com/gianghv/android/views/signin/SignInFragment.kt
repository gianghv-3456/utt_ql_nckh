package com.gianghv.android.views.signin

import android.annotation.SuppressLint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentSignInBinding
import com.gianghv.android.domain.AppState
import com.gianghv.android.domain.UserRole
import com.gianghv.android.views.AuthActivity
import com.gianghv.android.views.common.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {
    private val viewModel: AuthViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_sign_in

    private var activity: AuthActivity? = null

    private val authViewModel: AuthViewModel by viewModels()

    override fun init() {
        activity = requireActivity() as AuthActivity
    }

    override fun setUp() {
        Glide.with(this).load(activity?.getDrawable(R.drawable.ic_uni)).centerInside().into(binding.imageLogo)

        binding.apply {
            tvTitle.text = SpannableString("Welcome Back").apply {
                setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.blue_dark)),
                    8,
                    12,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            btnLogin.setOnClickListener {
                onClickLogin()
            }

            layoutSignUp.setOnClickListener {
                navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
            }
        }

        lifecycleScope.launch {
            authViewModel.responseMessage.collect {
                activity?.showMessage(it.message, it.bgType)
            }
        }
        lifecycleScope.launch {
            authViewModel.isLoading.collect {
                activity?.showLoading(isShow = it)
            }
        }

        viewModel.userRole.observe(viewLifecycleOwner) {
            if (it != null) {
                AppState.userRole = it
                AppState.logined = true
                navigate(SignInFragmentDirections.actionSignInFragmentToMainActivity())
            }
        }
    }

    override fun initData() {
    }

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentSignInBinding.inflate(inflater)

    @SuppressLint("SetTextI18n")
    private fun onClickLogin() {
        activity?.hideKeyboard()
        val email = binding.edtEmail.text?.trim().toString()
        val pass = binding.edtPassword.text?.trim().toString()
//        if (!AppUtils.isValidatedEmail(email)) {
//            activity?.showMessage("Email không hợp lệ!", BGType.BG_TYPE_ERROR)
//            binding.edtEmail.setText("")
//            return
//        }
//        if (!AppUtils.isValidatedPassword(pass)) {
//            activity?.showMessage("Mật khẩu không hợp lệ!", BGType.BG_TYPE_ERROR)
//            binding.edtPassword.setText("")
//            return
//        }
        val signInResult = authViewModel.signIn(email, pass)

//        when(signInResult) {
//            UserRole.RESEARCHER -> {
//                AppState.userRole = UserRole.RESEARCHER
//                AppState.logined = true
//                navigate(SignInFragmentDirections.actionSignInFragmentToMainActivity())
//            }
//            UserRole.SUPERVISOR -> {
//                AppState.userRole = UserRole.SUPERVISOR
//                AppState.logined = true
//                navigate(SignInFragmentDirections.actionSignInFragmentToMainActivity())
//            }
//            UserRole.ADMIN -> {
//                AppState.userRole = UserRole.ADMIN
//                AppState.logined = true
//                navigate(SignInFragmentDirections.actionSignInFragmentToMainActivity())
//            }
//            else -> {
//                activity?.showMessage("Tài khoản không tồn tại!", BGType.BG_TYPE_ERROR)
//            }
//        }
    }
}
