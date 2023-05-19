package com.example.carmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var createAcc: Button
    private lateinit var emailedt: EditText
    private lateinit var passwordedt: EditText

    private lateinit var inputphone: EditText

    //initialize firebase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //find our views
        emailedt = findViewById(R.id.edtEmail)
        passwordedt = findViewById(R.id.edtPassword)
        btnLogin = findViewById(R.id.login_btn)
        createAcc = findViewById(R.id.acc_btn)

        //initialize firebase
        auth = Firebase.auth


        createAcc.setOnClickListener {

            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



        btnLogin.setOnClickListener {

            loginUser()

        }





    }

    private fun loginUser() {
        //Toast.makeText(this, "Trying to Login", Toast.LENGTH_SHORT).show()
        //Convert inputs to string
        val userEmail = emailedt.text.toString()
        val userPassword = passwordedt.text.toString()

        //Toast.makeText(this, "email is $userEmail and password is $userPassword", Toast.LENGTH_LONG).show()

        auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener {
            if (it.isSuccessful) {
                //means if task is successful navigate to landing page
                var intent = Intent(this, Landingpage::class.java)
                startActivity(intent)


            }
            else {
                Toast.makeText(this, "Failed to Sign in", Toast.LENGTH_LONG).show()

                Log.d("TAG", "Error--->", it.exception)
            }
        }




    }
}