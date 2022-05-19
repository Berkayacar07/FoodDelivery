package com.mobil.fooddelivery.Customer

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mobil.fooddelivery.databinding.ActivityCustomerAddCartBinding
import java.text.DecimalFormat

class CustomerAddCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerAddCartBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference
    private var mStorageRef: StorageReference? = null

    lateinit var name : String
    var price : Double = 0.0
    lateinit var category : String
    var foodCount = 1

    private var selectedBitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerAddCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        foodCount=1

        database = Firebase.database.getReferenceFromUrl("https://fooddelivery-847b7-default-rtdb.firebaseio.com/")

        storage = FirebaseStorage.getInstance()

        mStorageRef = FirebaseStorage.getInstance().reference

        val intent = intent
        name = intent.getStringExtra("name").toString()
        val tempPrice = intent.getStringExtra("price").toString()
        price = tempPrice.toDouble()
        println(price)
        category = intent.getStringExtra("category").toString()

        val numberFormat = DecimalFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2

        binding.textViewCustomerAddArtFoodName.text = name
        binding.textViewCustomerAddArtFoodPrice.text = numberFormat.format(price)

        val photoId = storage.reference.child("images/$name.jpeg")

        val ONE_MEGABYTE: Long = 1024 * 1024
        photoId.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
            selectedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            binding.imageViewLoading.setImageBitmap(selectedBitmap)
        }.addOnFailureListener {
            // Handle any errors
        }


    }

    fun plusFood (view: View) {
        foodCount++
        update()
    }

    fun minusFood (view: View) {
        if (foodCount>=2) {
            foodCount--
        }
        update()
    }

    private fun update () {
        val numberFormat = DecimalFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2
        binding.textViewCustomerAddArtFoodPrice.text = numberFormat.format((price*foodCount))
        binding.editTextCountOfFood.setText(foodCount.toString())
    }

    fun addCart (view: View) {
        val intent = Intent(this, CustomerCartActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("price", price.toString())
        intent.putExtra("count", foodCount.toString())
        intent.putExtra("category", category)
        startActivity(intent)
        finish()
    }


}