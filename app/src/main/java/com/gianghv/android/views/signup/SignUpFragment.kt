package com.gianghv.android.views.signup

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentSignUpBinding
import com.gianghv.android.domain.UserRole
import com.gianghv.android.util.app.AppUtils
import com.gianghv.android.views.AuthActivity
import com.gianghv.android.views.common.AuthViewModel
import com.gianghv.android.views.common.BGType
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {
    val viewModel: AuthViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_sign_up

    private var activity: AuthActivity? = null

    private val authViewModel: AuthViewModel by viewModels()

    override fun init() {
        activity = requireActivity() as AuthActivity
    }

    override fun setUp() {
        binding.apply {
            btnNext.setOnClickListener {
                onClickNext()
            }
            layoutTitle.setOnClickListener {
                findNavController().popBackStack()
            }
            layoutBirthday.setOnClickListener {
                openPickDate { date ->
                    tvDate.text = date
                }
            }
        }

        collectLifecycleFlow(authViewModel.responseMessage) {
            activity?.showMessage(it.message, it.bgType)
        }
        collectLifecycleFlow(authViewModel.isLoading) {
            activity?.showLoading(isShow = it)
        }
    }

    override fun initData() {
    }

    override fun inflateViewBinding(inflater: LayoutInflater) = FragmentSignUpBinding.inflate(inflater)

    private fun openPickDate(onPicked: (String) -> Unit) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(requireContext(), { _, y, monthOfYear, dayOfMonth ->
            val d = if (dayOfMonth >= 10) dayOfMonth.toString() else "0$dayOfMonth"
            val m = if (monthOfYear + 1 >= 10) (monthOfYear + 1).toString() else "0${monthOfYear + 1}"
            onPicked("$d/$m/$y")
        }, year, month, day)
        dpd.show()
    }

    @SuppressLint("SetTextI18n")
    private fun onClickNext() {
        activity?.hideKeyboard()
        val email = binding.edtEmail.text?.trim().toString()
        val name = binding.edtName.text?.trim().toString()
        val pass = binding.edtPassword.text?.trim().toString()
        val confirm = binding.edtConfirmPassword.text?.trim().toString()
        val birthday = binding.tvDate.text.toString()
        if (!AppUtils.isValidatedName(name)) {
            activity?.showMessage("Tên không hợp lệ!", BGType.BG_TYPE_ERROR)
            binding.edtName.setText("")
            return
        }
        if (birthday.isEmpty()) {
            activity?.showMessage("Ngày sinh không hợp lệ!", BGType.BG_TYPE_ERROR)
            return
        }
        if (!AppUtils.isValidatedEmail(email)) {
            activity?.showMessage("Email không hợp lệ!", BGType.BG_TYPE_ERROR)
            binding.edtEmail.setText("")
            return
        }
        if (!AppUtils.isValidatedPassword(pass)) {
            activity?.showMessage("Mật khẩu không hợp lệ!", BGType.BG_TYPE_ERROR)
            binding.edtPassword.setText("")
            return
        }
        if (!AppUtils.isValidatedConfirmPass(pass, confirm)) {
            activity?.showMessage("Xác nhận mật khẩu không hợp lệ!", BGType.BG_TYPE_ERROR)
            binding.edtConfirmPassword.setText("")
            return
        }
        val value = RegisterInfo(
            name = name,
            email = email,
            password = pass,
            birthday = birthday,
            role = if (binding.rbNCS.isChecked) UserRole.RESEARCHER else UserRole.SUPERVISOR
        )
        navigate(SignUpFragmentDirections.actionSignUpFragmentToExtendRegisterFragment(value))
    }
}
