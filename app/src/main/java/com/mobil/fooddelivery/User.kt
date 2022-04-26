package com.mobil.fooddelivery

class User {

    var phone: String = ""
    var name: String = ""
    var password: String = ""

    constructor(phone: String, name: String, password: String) {
        this.phone = phone
        this.name = name
        this.password = password
    }

    constructor() {

    }

}
