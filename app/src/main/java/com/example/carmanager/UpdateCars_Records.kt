package com.example.carmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class UpdateCar_Record : AppCompatActivity() {
    lateinit var edt_car_make:EditText
    lateinit var edt_car_model:EditText
    lateinit var edt_car_price:EditText

    lateinit var update_data:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_car_record)

        edt_car_make = findViewById(R.id.edtcarmake)
        edt_car_model = findViewById(R.id.edtcarmodel)
        edt_car_price = findViewById(R.id.edtcarprice)
        update_data = findViewById(R.id.btnUpdateRec)

        //Receive data from intent

        var recieved_carmake = intent.getStringExtra("car_make")
        var recieved_carmodel  = intent.getStringExtra("car_model")
        var recieved_carprice  = intent.getStringExtra("car_price")
        var recieved_carid  = intent.getStringExtra("car_id")

        //Display in edit text
        edt_car_make.setText(recieved_carmake)
        edt_car_model.setText(recieved_carmodel)
        edt_car_price.setText(recieved_carprice)


        var db = FirebaseDatabase.getInstance()







        update_data.setOnClickListener {
            var carMake = edt_car_make.text.toString().trim()
            var carModel = edt_car_model.text.toString().trim()
            var carPrice = edt_car_price.text.toString().trim()
            //to make unique id in our table
            var id = recieved_carid!!

            var ref = db.getReference().child("cars/"+id)

            //validate user input
            if (carMake.isEmpty() || carModel.isEmpty() || carPrice.isEmpty() ) {

                Toast.makeText(this, "Cannot submit an empty field", Toast.LENGTH_SHORT).show()
            }
            else {

                //proceed to upload data
                var car = Cars(carMake, carModel, carPrice,id)

                ref.setValue(car).addOnCompleteListener {
                    if (it.isSuccessful) {

                        Toast.makeText(this, "Car Data Updated Successfully", Toast.LENGTH_SHORT).show()

                        //after successful update move from this activity to view cars
                        startActivity(Intent(this, ViewCars::class.java))
                        finish()

                    } else {

                        Toast.makeText(this, "Failed to Update Data", Toast.LENGTH_SHORT).show()


                    }
                }


            }



            //Toast.makeText(this, "Trying to submit data", Toast.LENGTH_SHORT).show()
        }






//        var database = FirebaseDatabase.getInstance()
//        var databaseRef = database.getReference("Cars")









    }
}