package com.example.markwiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class teacherActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        val teaName = findViewById<TextInputEditText>(R.id.teachName)
        val teaPassword = findViewById<TextInputEditText>(R.id.password)
        val logBtn = findViewById<Button>(R.id.loginBtn)

        logBtn.setOnClickListener{
            val teanString = teaName.text.toString()
            val teapString = teaPassword.text.toString()

            database = FirebaseDatabase.getInstance().getReference("teachLogDets")
            database.child(teanString).get().addOnSuccessListener {
                if(it.exists())
                {
                    val passActual = it.child("password").value.toString()
                    if(passActual == teapString)
                    {
                        Toast.makeText(this, "Correct Login!", Toast.LENGTH_SHORT).show()
                        val teachIntent = Intent(applicationContext, teachShow::class.java)
                        startActivity(teachIntent)
                    }
                    else
                    {
                        Toast.makeText(this, "wrong! try again", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "User doesnt exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}