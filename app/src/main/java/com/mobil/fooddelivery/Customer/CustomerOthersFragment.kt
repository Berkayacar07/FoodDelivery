package com.mobil.fooddelivery.Customer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.mobil.fooddelivery.R
import com.mobil.fooddelivery.Restaurant.RestaurantProfileFragment
import com.mobil.fooddelivery.databinding.FragmentCustomerOthersBinding


class CustomerOthersFragment : Fragment() {
    private lateinit var binding: FragmentCustomerOthersBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseAuth = FirebaseAuth.getInstance()
        binding = FragmentCustomerOthersBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerProfileButton.setOnClickListener {
            customerProfileOnClick(it)
        }
        binding.customerAddressButton.setOnClickListener {
            customerAddressOnClick(it)
        }
        binding.customerOrdersHistoryButton.setOnClickListener {
            customerOrdersHistoryOnClick(it)
        }
        binding.customerLogOutButton.setOnClickListener {
            customerLogOutOnClick(it)
        }
    }

    fun customerProfileOnClick (view:View){
        val fragment2 = CustomerProfileFragment()
        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentCustomerContainerView, fragment2)
        fragmentTransaction.commit()

    }
    fun customerAddressOnClick(view: View){

    }
    fun customerOrdersHistoryOnClick(view: View){

    }

    fun customerLogOutOnClick(view: View){
        activity?.let{
            firebaseAuth.signOut()
            val intent = Intent(it, CustomerLogInActivity::class.java)
            it.startActivity(intent)
        }
    }
}