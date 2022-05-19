package com.mobil.fooddelivery.Customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mobil.fooddelivery.databinding.ActivityCustomerCartBinding
import java.text.DecimalFormat


class CustomerCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerCartBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference
    private var mStorageRef: StorageReference? = null

    lateinit var name : String
    var price : Double = 0.0
    private lateinit var category : String
    var foodCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        database = Firebase.database.getReferenceFromUrl("https://fooddelivery-847b7-default-rtdb.firebaseio.com/")

        storage = FirebaseStorage.getInstance()

        mStorageRef = FirebaseStorage.getInstance().reference

        val intent = intent
        if (intent.getStringExtra("activity")=="A"){

        }else{
            name = intent.getStringExtra("name").toString()
            val tempPrice = intent.getStringExtra("price").toString()
            price = tempPrice.toDouble()
            val tempCount =  intent.getStringExtra("count").toString()
            foodCount = tempCount.toInt()
            category = intent.getStringExtra("category").toString()
        }


        val numberFormat = DecimalFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
    }
}