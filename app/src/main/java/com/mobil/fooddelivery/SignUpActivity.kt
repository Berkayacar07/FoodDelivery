package com.mobil.fooddelivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mobil.fooddelivery.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper.getDBHelper(this)!!
    }

    fun save(view: View) {
        var fullName = binding.kayitName.text.toString()
        var phone = binding.kayitTelefon.text.toString()
        var password = binding.kayitParola.text.toString()
        var confirmPassword = binding.kayitParola2.text.toString()


        if (phone.length == 10 && password.equals(confirmPassword)) {

            var list: ArrayList<User> = dbHelper.readData()
            var isContains: Boolean = false
            for (i in 0 until list.size) {
                if (list.get(i).phone.equals(phone)) {
                    isContains = true
                }
            }
            if (!isContains) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                binding.kayitName.text.clear()
                dbHelper.insertData(phone, fullName, password)
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Zaten Oyle Kullanıcı var", Toast.LENGTH_LONG).show()
            }

        } else {
            if (phone.length != 10) {
                Toast.makeText(applicationContext, "Invalid Phone Number", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Passwords not same", Toast.LENGTH_LONG).show()
            }
        }


    }

    fun backToLogin(view: View) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}