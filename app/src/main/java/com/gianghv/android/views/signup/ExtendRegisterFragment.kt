package com.gianghv.android.views.signup

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gianghv.android.R
import com.gianghv.android.base.BaseFragment
import com.gianghv.android.databinding.FragmentExtendRegisterBinding
import com.gianghv.android.domain.UserRole
import com.gianghv.android.views.AuthActivity
import com.gianghv.android.views.common.BGType

class ExtendRegisterFragment : BaseFragment<FragmentExtendRegisterBinding>() {

    override val layoutRes = R.layout.fragment_extend_register

    private lateinit var activity: AuthActivity

    override fun init() {
        activity = requireActivity() as AuthActivity
    }

    override fun setUp() {
        binding.apply {
            layoutTitle.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        binding.btnSignUp.setOnClickListener {
            onClickSignUp()
        }

        val args: ExtendRegisterFragmentArgs by navArgs()
        val value = args.registerInfo
        binding.edtKhoa.hint = when (value.role) {
            UserRole.NCS -> {
                "Khoa"
            }

            UserRole.GVHD -> {
                "PhD."
            }

            UserRole.ADMIN -> {
                ""
            }
        }
    }

    private fun onClickSignUp() {
        val studentCode = binding.edtStudentCode.text?.trim().toString()
        val lop = binding.edtClass.text?.trim().toString()
        val khoa = binding.edtKhoa.text?.trim().toString()
        if (studentCode.isEmpty()) {
            activity.showMessage("Mã sinh viên không hợp lệ!", BGType.BG_TYPE_ERROR)
            return
        }
        if (lop.isEmpty()) {
            activity.showMessage("Lớp không hợp lệ!", BGType.BG_TYPE_ERROR)
            return
        }
        if (khoa.isEmpty()) {
            activity.showMessage("Khoa không hợp lệ!", BGType.BG_TYPE_ERROR)
            return
        }
        navigate(ExtendRegisterFragmentDirections.actionExtendRegisterFragmentToMainActivity())
    }
}
