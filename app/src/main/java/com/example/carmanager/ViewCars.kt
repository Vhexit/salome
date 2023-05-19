package com.example.carmanager


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ViewCars : AppCompatActivity() {
    //initilaise your listview
    lateinit var car_list:ListView

    //array list
    lateinit var cars: ArrayList<Cars>

    //declare adapter
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_cars)

        car_list = findViewById(R.id.mycarlist)

        cars = ArrayList()

        adapter = CustomAdapter(this, cars)

        var ref = FirebaseDatabase.getInstance().getReference().child("cars")

        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                cars.clear()

                //loop to insert and display
                for (snap in snapshot.children){
                    var usercar = snap.getValue(Cars::class.java)
                    cars.add(usercar!!)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@ViewCars, "Database Inaccessible", Toast.LENGTH_SHORT).show()
            }
        })

        car_list.adapter = adapter



    }
}