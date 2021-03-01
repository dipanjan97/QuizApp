package com.example.quizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val username=intent.getStringExtra(Constants.USER_NAME)
        user.text=username

        val totalQ=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        val correctA=intent.getIntExtra(Constants.CORRECT_ANSWERS,0)

        resultText.text="Your score is $correctA out of $totalQ"

        finishButton.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }


    }
}