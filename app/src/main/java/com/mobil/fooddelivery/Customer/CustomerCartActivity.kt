package com.mobil.fooddelivery.Customer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mobil.fooddelivery.databinding.ActivityCustomerCartBinding
import java.text.DecimalFormat
import java.util.ArrayList


class CustomerCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerCartBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference
    private var mStorageRef: StorageReference? = null
    private lateinit var userID : String
    private lateinit var recyclerView : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

        database = Firebase.database.getReferenceFromUrl("https://fooddelivery-847b7-default-rtdb.firebaseio.com/")

        storage = FirebaseStorage.getInstance()

        mStorageRef = FirebaseStorage.getInstance().reference

        userID = FirebaseAuth.getInstance().uid.toString()

        val numberFormat = DecimalFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 2

        val list = ArrayList<FoodCart>()

        recyclerView = binding.recyclerViewCartPage
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, DividerItemDecoration.VERTICAL_LIST
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()

        var x = 0

        database.child(userID).child("Carts").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){

                    val tempCount = i.child("foodCount").value
                    val tempName = i.child("foodName").value
                    val tempPrice = i.child("foodPrice").value
                    println(tempName.toString() + " " + " " + tempCount.toString()+ "      "+ tempPrice.toString())
                    list.add(FoodCart(tempName.toString(),tempPrice.toString(),tempCount.toString()))
                    x++
                    if (x == snapshot.childrenCount.toInt()) {
                        recyclerView.adapter =
                            CustomerCartRecyclerAdapter(list)
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })









    }

    private fun checkUser() {

        val firebaseUser = firebaseAuth.currentUser

        if(firebaseUser != null) {

            val email = firebaseUser.email
        }else {
            startActivity(Intent(this,CustomerLogInActivity::class.java))
            finish()
        }
    }

    fun customerHome(view: View){
        val intent = Intent(applicationContext, CustomerMainPageActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun customerBasket(view: View){
        val intent = Intent(applicationContext, CustomerCartActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun customerSearch(view: View){
        val intent = Intent(applicationContext, CustomerSearchActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun continueButton(view: View){
        val intent = Intent(applicationContext, CustomerPaymentPageActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun customerLine(view: View){
        val intent = Intent(applicationContext, CustomerMainPageActivity::class.java)
        intent.putExtra("Activity", "Go to others")
        startActivity(intent)
        finish()
    }

}