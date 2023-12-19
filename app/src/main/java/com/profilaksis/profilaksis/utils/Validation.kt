package com.profilaksis.profilaksis.utils

fun isValidEmail(email: String): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+"
    return email.matches(emailPattern.toRegex())
}


fun isValidPassword(password: String): Boolean {
    return password.length >= 8
}
