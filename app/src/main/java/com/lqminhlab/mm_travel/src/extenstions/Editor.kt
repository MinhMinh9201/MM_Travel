package com.lqminhlab.mm_travel.src.extenstions

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

fun EditText.validate(validator: (String) -> Boolean, messageError: String) : Boolean {
    this.afterTextChanged {
        this.error = if (validator(it)) null else messageError
    }
    return validator(this.text.toString())
}

fun EditText.afterTextChanged(afterTextChanged : (String)->Unit){
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun String.isValidLength(length: Int): Boolean  = this.length >= length
fun String.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()