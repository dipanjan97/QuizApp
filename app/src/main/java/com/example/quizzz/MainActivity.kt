package com.example.quizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton.setOnClickListener {

            if(Username.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please enter your name to proceed",Toast.LENGTH_SHORT).show()
            }else {

                val mainIntent= Intent(this, QuizQuestionsActivity::class.java )
                startActivity(mainIntent)
                finish()
            }
        }
    }


}