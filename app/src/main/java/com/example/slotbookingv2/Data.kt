package com.example.slotbookingv2

class Data(
    val id: String,
    val email: String,
    val pass: String,
    val name: String,
    val number: String,
    val studentId: String,
    status: String,
    flag1: String,
    flag2: String
) {
    constructor() : this("", "", "", "", "", "", "", "", "")

}
/*

class Data(val id: String, val email: String, val pass: String){
    constructor() : this("", "", "")
}*/
