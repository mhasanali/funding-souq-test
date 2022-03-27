package com.hassan.fundingsouqtest.data.response

import com.hassan.fundingsouqtest.utilities.enums.Sex

data class User(
    val email: String, val firstName: String, val lastName: String, val personalId:
    String, val profilePhotoUrl: String, val mobileNumber: Int, val sex: Sex, val address: Address,
    val accounts:
    List<Account>
)

data class Address(val country: String, val city: String, val street: String, val zipCode: String)

data class Account(val title: String, val number: Double)
