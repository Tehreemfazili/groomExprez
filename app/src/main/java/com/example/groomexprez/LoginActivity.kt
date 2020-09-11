package com.example.groomexprez

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.example.groomexprez.common.BaseActivity
import com.example.groomexprez.databinding.ActivityLoginBinding
import com.example.groomexprez.utils.clearErrorMessage
import com.example.groomexprez.utils.setErrorMessage

class LoginActivity : BaseActivity(){

    private val binding : ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        attachListener()

        binding.btnLogin.setOnClickListener {
            if(isValid())
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegistrationActivty::class.java))
        }

    }

    private fun isValid() : Boolean{

        val password = binding.etPassword.text

        if (TextUtils.isEmpty(password)) {

            binding.tilPassword.setErrorMessage(getString(R.string.invalid_password))
            return false
        } else if (
            TextUtils.getTrimmedLength(password) < 8) {

            binding.tilPassword.setErrorMessage(getString(R.string.enter_password))
            return false
        }
        return true
    }

    private fun attachListener() {
        binding.etName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilName.clearErrorMessage()
            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.tilPassword.clearErrorMessage()
            }

        })
    }
}