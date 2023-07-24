package com.example.markwiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class teachShow : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teach_show)

        val entName = findViewById<TextInputEditText>(R.id.inpName)
        val entMarks = findViewById<TextInputEditText>(R.id.inpMarks)
        val btnAdd = findViewById<Button>(R.id.addBtn)

        val nameShow = findViewById<TextInputEditText>(R.id.viewStu)
        val btnShow = findViewById<Button>(R.id.showBtn)
        val markShow = findViewById<TextView>(R.id.showMark)

        btnAdd.setOnClickListener{
            val nameTo = entName.text.toString()
            val markTo = entMarks.text.toString()

            val user = stuLogDets(markTo)

            database = FirebaseDatabase.getInstance().getReference("studMarks")
            database.child(nameTo).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "Marks Added!", Toast.LENGTH_SHORT).show()
                entName.text = null
                entMarks.text = null
            }.addOnFailureListener{
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }
        }

        btnShow.setOnClickListener{
            val nameStr = nameShow.text.toString()
            database = FirebaseDatabase.getInstance().getReference("studMarks")
            database.child(nameStr).get().addOnSuccessListener {
                if(it.exists())
                {
                    val gotMarks = it.child("marks").value
                    markShow.text = gotMarks.toString()
                }
                else
                {
                    Toast.makeText(this, "Student and mark doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, "failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}