package com.mobil.fooddelivery.Restaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.mobil.fooddelivery.Customer.CustomerLogInActivity
import com.mobil.fooddelivery.databinding.ActivityRestaurantLogInBinding

const val restaurantEmail = "colak4364@gmail.com"
var restaurantPassword = "admin"
var restaurantName = "WhiteSea Restaurant"
var restaurantAddress = "Akdeniz University Campus"

class RestaurantLogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun login(view: View) {
        var email = binding.editTextRestaurantLogInEmail.text.toString().trim()
        var password = binding.editTextRestaurantLogInPassword.text.toString().trim()


        if (email == restaurantEmail) {
            if (password == restaurantPassword) {
                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, RestaurantMainPageActivity::class.java)
                intent.putExtra("Email",email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(applicationContext, "Wrong Password", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(applicationContext, "Wrong E mail", Toast.LENGTH_LONG).show()
        }
    }

    fun customer (view : View) {
        val intent = Intent (applicationContext, CustomerLogInActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun forgotPassword (view : View) {
        val intent = Intent (applicationContext, RestaurantForgotPassword::class.java)
        startActivity(intent)
        finish()
    }

    fun changeName (name : String) {
        restaurantName = name
    }

    fun changePassword (password : String) {
        restaurantPassword =  password
    }

    fun changeAddress (address : String) {
        restaurantAddress = address
    }

}