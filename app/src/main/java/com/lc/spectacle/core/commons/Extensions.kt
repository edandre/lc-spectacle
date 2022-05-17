package com.lc.spectacle.core.commons

import android.text.TextUtils
import android.util.Patterns

object Extensions {
    fun String?.isValidEmail(): Boolean {
        return !this.isNullOrBlank() && !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }
}