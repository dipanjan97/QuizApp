package com.example.quizzz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz_questions.*

class QuizQuestionsActivity : AppCompatActivity() , View.OnClickListener{

    private var mCurrentQuestion:Int=1
    private var mQuestionList:ArrayList<Question>?=null
    private var mSelectedOption:Int=0
    private var mCorrectAnswer:Int=0
    private var mUsername:String?=null
    private var mCorrectAnswers:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_quiz_questions)

        mUsername=intent.getStringExtra(Constants.USER_NAME)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        mQuestionList=Constants.getQuestions()


        setQuestion()

        OptionOne.setOnClickListener(this)
        OptionTwo.setOnClickListener(this)
        OptionThree.setOnClickListener(this)
        OptionFour.setOnClickListener(this)
        SubmitButton.setOnClickListener(this)
    }

    private fun setQuestion(){

        val question:Question?=mQuestionList!![mCurrentQuestion-1]

        defaultOptionsView()

        if(mCurrentQuestion==mQuestionList!!.size){
            SubmitButton.text="FINISH"
        }
        else{
            SubmitButton.text="SUBMIT"
        }

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

            Option.setTextColor(Color.parseColor("#FFFFFF"))
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
            R.id.SubmitButton->{
                if(mSelectedOption==0){
                    mCurrentQuestion++

                    when{
                        mCurrentQuestion<=mQuestionList!!.size-> {
                            setQuestion()
                        }
                        else->{
                            val intent=Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUsername)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionList!!.size)
                            startActivity(intent)
                        }

                    }
                }else{
                    val question=mQuestionList?.get(mCurrentQuestion-1)
                    if(question!!.correctAnswer!=mSelectedOption)
                        answerView(mSelectedOption,R.drawable.wrong_option_border)
                    else{
                        mCorrectAnswers++
                    }


                    answerView(question.correctAnswer,R.drawable.correct_option_border)

                    if(mCurrentQuestion==mQuestionList!!.size){
                        SubmitButton.text="FINISH"
                    }
                    else{
                        SubmitButton.text="GO TO NEXT QUESTION"
                    }
                    mSelectedOption = 0

                }
            }

        }
    }

    private fun selectOptionView(tv:TextView,selectOptionNumber:Int){

        defaultOptionsView()
        mSelectedOption=selectOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background=ContextCompat.getDrawable(this,R.drawable.select_option_border)

    }

    private fun answerView(ans:Int, drawableView:Int){
        when(ans){
            1-> OptionOne.background=ContextCompat.getDrawable(this,drawableView)
            2-> OptionTwo.background=ContextCompat.getDrawable(this,drawableView)
            3-> OptionThree.background=ContextCompat.getDrawable(this,drawableView)
            4-> OptionFour.background=ContextCompat.getDrawable(this,drawableView)
        }
    }
}