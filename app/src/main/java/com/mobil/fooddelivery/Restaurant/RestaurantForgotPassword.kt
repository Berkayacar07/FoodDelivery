package com.mobil.fooddelivery.Restaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobil.fooddelivery.databinding.ActivityRestaurantForgotPasswordBinding

class RestaurantForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}