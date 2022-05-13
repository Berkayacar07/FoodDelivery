package com.mobil.fooddelivery.Customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobil.fooddelivery.databinding.ActivityCustomerForgotPasswordBinding

class CustomerForgotPassword : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}