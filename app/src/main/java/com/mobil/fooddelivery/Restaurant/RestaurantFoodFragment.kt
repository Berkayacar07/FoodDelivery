package com.mobil.fooddelivery.Restaurant

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.mobil.fooddelivery.databinding.FragmentRestaurantFoodBinding




class RestaurantFoodFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantFoodBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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