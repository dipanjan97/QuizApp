package com.example.quizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        startButton.setOnClickListener {

            if(Username.text.toString().isEmpty())
            {
                Toast.makeText(this,"Please enter your name to proceed",Toast.LENGTH_SHORT).show()
            }else {

                val mainIntent= Intent(this, QuizQuestionsActivity::class.java )
                mainIntent.putExtra(Constants.USER_NAME,Username.text.toString())
                startActivity(mainIntent)
                finish()
            }
        }
    }


}