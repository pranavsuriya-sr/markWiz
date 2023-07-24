package com.example.markwiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val teachIntent = findViewById<Button>(R.id.teachBtn)
        teachIntent.setOnClickListener{
            intent = Intent(applicationContext, teacherActivity::class.java)
            startActivity(intent)
        }

        val studIntent = findViewById<Button>(R.id.studBtn)
        studIntent.setOnClickListener{
            intent = Intent(applicationContext, studActivity::class.java)
            startActivity(intent)
        }
    }
}