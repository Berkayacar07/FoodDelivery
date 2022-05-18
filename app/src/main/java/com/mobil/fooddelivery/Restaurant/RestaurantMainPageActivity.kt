package com.mobil.fooddelivery.Restaurant


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mobil.fooddelivery.R


class RestaurantMainPageActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference
    private lateinit var timeDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_main_page)

        firebaseAuth = FirebaseAuth.getInstance()

        database = Firebase.database.getReferenceFromUrl("https://fooddelivery-847b7-default-rtdb.firebaseio.com/")

        storage = FirebaseStorage.getInstance()
        val foodPhotoList : ArrayList<Bitmap> = ArrayList()
        val foodNameList : ArrayList<String> = ArrayList()
        val foodPriceList : ArrayList<String> = ArrayList()
        val foodCategoryList : ArrayList<String> = ArrayList()

        database.child("Food").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(i in snapshot.children){
                    var foodName =i.child("name").value.toString()
                    var foodPrice =i.child("price").value.toString()
                    var foodCategory =i.child("category").value.toString()

                    foodNameList.add(foodName)
                    foodPriceList.add(foodPrice)
                    foodCategoryList.add(foodCategory)

                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        for (imageName in foodNameList) {
            val photoId: String = "$imageName.jpeg"
            val photoRef: StorageReference = storage.reference.child("images/$photoId")
            photoRef.getBytes((1024 * 1024).toLong()).addOnSuccessListener { bytes ->
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                foodPhotoList.add(bitmap)
                println(5)
                println("size"+foodPhotoList.size)
                println(bitmap.height)
            }.addOnFailureListener { }
        }
        println(5)



        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,RestaurantFoodFragment())
        fragmentTransaction.commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_food, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add_food_item) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerView,RestaurantAddFoodFragment())
            fragmentTransaction.commit()
        }

        return super.onOptionsItemSelected(item)
    }

    fun home (view: View) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,RestaurantFoodFragment())
        fragmentTransaction.commit()
    }

    fun others (view: View) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView,RestaurantOthersFragment())
        fragmentTransaction.commit()
    }


}