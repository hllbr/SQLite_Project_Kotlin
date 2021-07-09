package com.hllbr.sqliteprojectkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        Try-CATCH BİR HATA ELE ALMA YÖNTEMİDİR
        Veri tabanları çalıştığı activiteden yada Context'ten oluşturulabilir.
         */
    try{
        val myDataBase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR,age INT)")
        myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('JAMES',50)")

        val cursor = myDataBase.rawQuery("SELECT * FROM musicians",null)

        val nameIx = cursor.getColumnIndex("name")
        val ageIx = cursor.getColumnIndex("age")

        while (cursor.moveToNext()){
            println("name : "+cursor.getString(nameIx))
            println("age : "+cursor.getInt(ageIx))
        }
        cursor.close()
    }catch (e : Exception){
        e.printStackTrace()
    }
    }
}