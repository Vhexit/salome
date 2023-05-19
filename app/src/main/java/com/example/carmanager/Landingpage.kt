package com.example.carmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Landingpage : AppCompatActivity() {
    lateinit var edt_car_make:EditText
    lateinit var edt_car_model:EditText
    lateinit var edt_car_price:EditText
    lateinit var submit_data:Button
    lateinit var viewcarsbtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landingpage)

        edt_car_make = findViewById(R.id.edtcarmake)
        edt_car_model = findViewById(R.id.edtcarmodel)
        edt_car_price = findViewById(R.id.edtcarprice)
        submit_data = findViewById(R.id.btnsubmit)

        var db = FirebaseDatabase.getInstance()

        viewcarsbtn = findViewById(R.id.btnviewcars)



        viewcarsbtn.setOnClickListener {

            var intent = Intent(this, ViewCars::class.java)
            startActivity(intent)
        }





        submit_data.setOnClickListener {
            var carMake = edt_car_make.text.toString().trim()
            var carModel = edt_car_model.text.toString().trim()
            var carPrice = edt_car_price.text.toString().trim()
            //to make unique id in our table
            var id = System.currentTimeMillis().toString()

            var ref = db.getReference("cars/"+id)

            //validate user input
            if (carMake.isEmpty() || carModel.isEmpty() || carPrice.isEmpty() ) {

                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }
            else {

                //proceed to upload data
                var car = Cars(carMake, carModel, carPrice,id)

                ref.setValue(car).addOnCompleteListener {
                    if (it.isSuccessful) {

                        Toast.makeText(this, "Car Data Uploaded Successfully", Toast.LENGTH_SHORT).show()

                    } else {

                        Toast.makeText(this, "Failed to Upload Data", Toast.LENGTH_SHORT).show()


                    }
                }


            }



            //Toast.makeText(this, "Trying to submit data", Toast.LENGTH_SHORT).show()
        }






//        var database = FirebaseDatabase.getInstance()
//        var databaseRef = database.getReference("Cars")









    }
}