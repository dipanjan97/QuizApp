package com.example.quizzz

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentQuestion:Int=0
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOption:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        mQuestionList=Constants.getQuestions()


        setQuestion()

        OptionOne.setOnClickListener(this)
        OptionTwo.setOnClickListener(this)
        OptionThree.setOnClickListener(this)
        OptionFour.setOnClickListener(this)
    }

    private fun setQuestion(){

        mCurrentQuestion=1
        val question:Question?=mQuestionList!![mCurrentQuestion-1]

        defaultOptionsView()

        progressBar.progress=mCurrentQuestion
        ProgressText.text= "$mCurrentQuestion"+ "/" + progressBar.max
        tvquestion.text=question!!.question
        flagImage.setImageResource(question.image)
        OptionOne.text=question.optionOne
        OptionTwo.text=question.optionTwo
        OptionThree.text=question.optionThree
        OptionFour.text=question.optionFour

    }

    private fun defaultOptionsView(){

        val options= ArrayList<TextView>()
        options.add(OptionOne)
        options.add(OptionTwo)
        options.add(OptionThree)
        options.add(OptionFour)

        for (Option in options){

            Option.setTextColor(Color.parseColor("#343434"))
            Option.typeface= Typeface.DEFAULT
            Option.background=ContextCompat.getDrawable(this,R.drawable.default_option_border)

        }


    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.OptionOne->selectOptionView(OptionOne,1)
            R.id.OptionTwo->selectOptionView(OptionTwo,2)
            R.id.OptionThree->selectOptionView(OptionThree,3)
            R.id.OptionFour->selectOptionView(OptionFour,4)

        }
    }

    private fun selectOptionView(tv:TextView,selectOptionNumber:Int){

        defaultOptionsView()
        mSelectedOption=selectOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(this,R.drawable.select_option_border)

    }
}