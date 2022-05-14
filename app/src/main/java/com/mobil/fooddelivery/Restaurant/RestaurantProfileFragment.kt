package com.mobil.fooddelivery.Restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mobil.fooddelivery.databinding.FragmentRestaurantProfileBinding


class RestaurantProfileFragment : Fragment() {
    private lateinit var binding: FragmentRestaurantProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRestaurantProfileBinding.inflate(inflater, container, false)
        return binding.root


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.editTextRestaurantPassword.setText(restaurantPassword)
        binding.editTextRestaurantAddress.setText(restaurantAddress)
        binding.editTextRestaurantName.setText(restaurantName)
        binding.updateButtonProfileRestaurant.setOnClickListener {
            update(it)
        }

    }

    fun update(view: View){

    }


}