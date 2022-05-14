package com.mobil.fooddelivery.Customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.mobil.fooddelivery.databinding.ActivityCustomerMainPageBinding


class CustomerMainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerMainPageBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar = supportActionBar!!
        actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }

    fun logOut (view : View) {
        firebaseAuth.signOut()
        checkUser()
    }

    private fun checkUser() {

        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null) {

            val email = firebaseUser.email

            binding.textViewCustomerMainPageInformation.text = email
        }else {
            startActivity(Intent(this,CustomerLogInActivity::class.java))
            finish()
        }
    }
}