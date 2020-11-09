package com.example.quizzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        val questionList=Constants.getQuestions()
        Log.i("Question size","${questionList.size}")

        val currentPos=1
        val question:Question?=questionList[currentPos-1]

        progressBar.progress=currentPos
        ProgressText.text= "$currentPos"+ "/" + progressBar.max
        tvquestion.text=question!!.question
        flagImage.setImageResource(question.image)
        OptionOne.text=question.optionOne
        OptionTwo.text=question.optionTwo
        OptionThree.text=question.optionThree
        OptionFour.text=question.optionFour





    }
}