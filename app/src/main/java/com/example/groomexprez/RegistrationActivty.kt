package com.example.groomexprez

import android.os.Bundle
import com.example.groomexprez.common.BaseActivity
import com.example.groomexprez.databinding.ActivityRegistrationBinding
import com.example.groomexprez.utils.setErrorMessage

class RegistrationActivty : BaseActivity() {

    private val binding: ActivityRegistrationBinding by lazy {
        ActivityRegistrationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            if (isValid()) {

            }

        }
    }

    private fun isValid(): Boolean {

        when {
            binding.etName.text.isNullOrBlank() -> {
                binding.tilName.setErrorMessage("Please enter name")
                binding.etName.requestFocus()
                return false
            }

            binding.etName.text.toString().length < 3 -> {
                binding.tilName.setErrorMessage("Invalid Name")
                binding.etName.requestFocus()
                return false

            }

            binding.etPassword.text.isNullOrBlank() -> {
                binding.tilPassword.setErrorMessage(getString(R.string.enter_password))
                binding.etName.requestFocus()
                return false
            }

            binding.etPassword.text.toString().length < 8 -> {
                binding.tilPassword.setErrorMessage(getString(R.string.invalid_password))
                binding.etName.requestFocus()
                return false
            }

        }
        return true
    }


}