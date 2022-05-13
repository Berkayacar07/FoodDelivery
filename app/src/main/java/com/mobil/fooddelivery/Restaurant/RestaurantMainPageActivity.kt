package com.mobil.fooddelivery.Restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.mobil.fooddelivery.databinding.ActivityRestaurantMainPageBinding


class RestaurantMainPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestaurantMainPageBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestaurantMainPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //actionBar = supportActionBar!!
        //actionBar.title = "Profile"

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textViewRestaurantMainPageInformation.text = "Restaurant Mobile"
    }

    fun logOut (view : View) {
        firebaseAuth.signOut()
        startActivity(Intent(this,RestaurantLogInActivity::class.java))
    }


}