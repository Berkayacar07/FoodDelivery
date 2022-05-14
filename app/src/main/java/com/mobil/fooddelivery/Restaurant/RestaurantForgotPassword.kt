package com.mobil.fooddelivery.Restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.mobil.fooddelivery.databinding.ActivityRestaurantForgotPasswordBinding

class RestaurantForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantForgotPasswordBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        actionBar.title = "Restaurant Forgot Password"

    }

}