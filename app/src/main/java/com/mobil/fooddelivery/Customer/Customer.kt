package com.mobil.fooddelivery.Customer

class Customer {

    var email: String = ""
    var fullName: String = ""
    var password: String = ""

    constructor(email: String, name: String, password: String) {
        this.email = email
        this.fullName = name
        this.password = password
    }

    constructor(name: String, password: String) {
        this.fullName = name
        this.password = password
    }

    constructor() {

    }


}