package com.example.slotbookingv2

class Data(
    val id: String,
    val email: String,
    val pass: String,
    val name: String,
    val number: String,
    val studentId: String,
    val status: String,
    val user_type: String
) {
    constructor() : this("", "", "", "", "", "", "", "")

}
/*
class Data(val id: String, val email: String, val pass: String){
    constructor() : this("", "", "")
}*/