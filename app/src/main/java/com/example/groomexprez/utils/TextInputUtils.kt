package com.example.groomexprez.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorMessage(errorMessage: String) {
    error = errorMessage
}

fun TextInputLayout.clearErrorMessage() {
    error = null
    isErrorEnabled = false
}