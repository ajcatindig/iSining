package com.xanthenite.isining.utils.validator

object AuthValidator {

    /** [username] */
    fun isValidUsername(username: String): Boolean = username.trim().length in (1..30)

    /** [email] */
    val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    fun isValidEmail(email : String) : Boolean { return EMAIL_REGEX.toRegex().matches(email) }

    /** [password] */
    fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)

    fun isPasswordAndConfirmPasswordSame(
            password: String,
            confirmedPassword: String): Boolean = password.trim() == confirmedPassword.trim()

}