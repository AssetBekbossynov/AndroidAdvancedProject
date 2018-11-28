package com.example.asset.todoproject.models

data class User (var password: String,
                 var email: String,
                 var friends: List<String>?){
    constructor(): this("", "", emptyList())
    override fun toString(): String {
        return "User(password='$password', email='$email', friends=$friends)"
    }
}