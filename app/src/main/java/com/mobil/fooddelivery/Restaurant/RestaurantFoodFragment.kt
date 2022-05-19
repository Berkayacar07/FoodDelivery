package com.mobil.fooddelivery.Restaurant

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
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
import com.mobil.fooddelivery.Restaurant.DividerItemDecoration.Companion.VERTICAL_LIST
import com.mobil.fooddelivery.databinding.FragmentRestaurantFoodBinding


class RestaurantFoodFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantFoodBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var database: DatabaseReference
    private lateinit var recyclerView : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

                for (imageName in foodNameList) {
                    var photoId = storage.reference.child("images/$imageName.jpeg")

                    val ONE_MEGABYTE: Long = 1024 * 1024
                    photoId.getBytes(ONE_MEGABYTE).addOnSuccessListener { bytes ->
                        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                        foodPhotoList.add(bitmap)
                    }.addOnFailureListener {
                        // Handle any errors
                    }
                    recyclerView.adapter =
                        RestaurantFoodRecyclerAdapter(foodNameList,foodPriceList,foodCategoryList)




                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    fun refresh () {
        recyclerView.invalidate()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireActivity(), VERTICAL_LIST
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRestaurantFoodBinding.inflate(inflater, container, false)
        return binding.root

    }



}