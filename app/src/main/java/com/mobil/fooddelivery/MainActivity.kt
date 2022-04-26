package com.mobil.fooddelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mobil.fooddelivery.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper.getDBHelper(this)!!


    }

    fun login (view: View) {
        var phone = binding.girisTelefon.text.toString()
        var password = binding.girisParola.text.toString()
        var list : ArrayList<User> = dbHelper.readData()
        var isContains : Boolean = false
        var isMatchedPassword : Boolean = false
        for (i in 0 until list.size) {
            if (list.get(i).phone.equals(phone)) {
                isContains = true
                if (list.get(i).password.equals(password)) {
                    isMatchedPassword = true
                }
            }
        }

        if (isContains) {
            if (isMatchedPassword) {
                Toast.makeText(applicationContext,"Login succesfull", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,SignUpActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext,"Wrong Password", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(applicationContext,"Do not found user", Toast.LENGTH_LONG).show()
        }
    }


    fun signUp(view: View){
        val intent = Intent(applicationContext,SignUpActivity::class.java)
        startActivity(intent)
    }


}