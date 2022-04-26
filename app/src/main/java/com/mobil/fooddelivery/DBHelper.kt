package com.mobil.fooddelivery

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "fooddelivery"
val TABLE_NAME = "users"
val COL_PHONE = "phone"
val COL_NAME = "name"
val COL_PASSWORD = "password"

class DBHelper private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    companion object {
        private var dBHelper: DBHelper? = null
        fun getDBHelper(context: Context): DBHelper? {
            if (dBHelper == null) {
                dBHelper = DBHelper(context)
            }
            return dBHelper
        }
    }

    var context: Context? = context
        private set
        get


    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COL_PHONE + " CHAR(10) PRIMARY KEY ," +
                COL_NAME + " VARCHAR(50) NOT NULL," +
                COL_PASSWORD + " VARCHAR(20) NOT NULL)"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")


    }


    fun insertData(phone: String, name: String, password: String) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_PHONE, phone)
        cv.put(COL_NAME, name)
        cv.put(COL_PASSWORD, password)
        var result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show()

        }
    }

    fun readData(): ArrayList<User> {
        var userlist: ArrayList<User> = ArrayList()


        val db = this.readableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User()
                var index1 = result.getColumnIndex(COL_PHONE)
                var index2 = result.getColumnIndex(COL_NAME)
                var index3 = result.getColumnIndex(COL_PASSWORD)
                user.phone = result.getString(index1)
                user.name = result.getString(index2)
                user.password = result.getString(index3)
                userlist.add(user)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return userlist

    }

}