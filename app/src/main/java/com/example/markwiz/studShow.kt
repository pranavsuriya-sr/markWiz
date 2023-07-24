package com.example.markwiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class studShow : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud_show)

        val nameShow = intent.getStringExtra(studActivity.KEY1)
        val nameText = findViewById<TextView>(R.id.textName)
        nameText.text = nameShow

        val nameData = nameText.text.toString()

        database = FirebaseDatabase.getInstance().getReference("studMarks")
        database.child(nameData).get().addOnSuccessListener {
            if(it.exists())
            {
                val markPass = it.child("marks").value
                val markShow = markPass.toString()

                val markText = findViewById<TextView>(R.id.textMarks)
                markText.text = markShow

            }
        }
    }
}