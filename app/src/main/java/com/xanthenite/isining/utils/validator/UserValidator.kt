package com.xanthenite.isining.utils.validator

object UserValidator
{
    /** [username] */
    fun isValidUsername(username: String): Boolean = username.trim().length in (1..30)

    /** [mobile_number] */
    val NUMBER_REGEX = "((^(\\+)(\\d){12}\$)|(^\\d{11}\$))"
    fun isValidMobileNumber(mobile_number : String) : Boolean { return NUMBER_REGEX.toRegex().matches(mobile_number) }

    /** [address] */
    fun isValidAddress(address : String) : Boolean = address.trim().length in (8..255)

    /** [desciption] */
    fun isValidDescription(description : String) : Boolean = description.trim().length in (8..255)

}