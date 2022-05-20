package com.mobil.fooddelivery.Customer

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.mobil.fooddelivery.databinding.ActivityCustomerPaymentPageBinding


class CustomerPaymentPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerPaymentPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerPaymentPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun customerHome(view: View){
        val intent = Intent(applicationContext, CustomerMainPageActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun customerBasket(view: View){
        val intent = Intent(applicationContext, CustomerCartActivity::class.java)
        intent.putExtra("activity","A")
        startActivity(intent)
        finish()
    }
    fun customerSearch(view: View){
        val intent = Intent(applicationContext, CustomerSearchActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun orderButton(view: View){
        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        val intent = Intent(applicationContext, CustomerMainPageActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Do you confirm the payment transaction?").setPositiveButton("Yes", dialogClickListener)
            .setNegativeButton("No", dialogClickListener).show()
    }
    fun customerLine(view: View){
        val intent = Intent(applicationContext, CustomerMainPageActivity::class.java)
        intent.putExtra("Activity","Go to others")
        startActivity(intent)
        finish()
    }
}