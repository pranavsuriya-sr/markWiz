package com.example.markwiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class studActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference

    companion object
    {
        const val KEY1 = "com.example.markWiz.studActivity.rollNo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stud)

        val tempRoll = findViewById<TextInputEditText>(R.id.rollNo)

        val tempPass = findViewById<TextInputEditText>(R.id.password)

        val tempLogBtn = findViewById<Button>(R.id.loginBtn)

        tempLogBtn.setOnClickListener{
            val strRoll = tempRoll.text.toString()
            val strPw = tempPass.text.toString()

            database = FirebaseDatabase.getInstance().getReference("studLogDets")
            database.child(strRoll).get().addOnSuccessListener {
                if(it.exists())
                {
                    val actPass = it.child("password").value.toString()

                    if(actPass == strPw)
                    {
                        Toast.makeText(this, "correct, Login!", Toast.LENGTH_SHORT).show()

                        val namePass = it.child("name").value
                        val intentShow = Intent(this, studShow::class.java)
                        intentShow.putExtra(KEY1, namePass.toString())
                        startActivity(intentShow)
                    }
                    else
                    {
                        Toast.makeText(this, "Wrong Pw, Retry!", Toast.LENGTH_SHORT).show()
                    }
                }
                else
                {
                    Toast.makeText(this, "user doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener{
                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        val regBtn = findViewById<Button>(R.id.regLog)
        val namLog = findViewById<TextInputEditText>(R.id.namLog)
        val rolLog = findViewById<TextInputEditText>(R.id.rolLog)
        val pwdLog = findViewById<TextInputEditText>(R.id.passLog)
        regBtn.setOnClickListener{
            val toName = namLog.text.toString()
            val toRoll = rolLog.text.toString()
            val toPass = pwdLog.text.toString()

            val user = stuEnroll(toName, toPass)

            database = FirebaseDatabase.getInstance().getReference("studLogDets")
            database.child(toRoll).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "Student registered", Toast.LENGTH_SHORT).show()
                namLog.text = null
                rolLog.text = null
                pwdLog.text = null
            }.addOnFailureListener{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}