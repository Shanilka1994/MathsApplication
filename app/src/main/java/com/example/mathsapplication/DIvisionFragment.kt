package com.example.mathsapplication

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random


class DIvisionFragment : Fragment() {
    private lateinit var number1TextView: TextView
    private lateinit var number2TextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var feedbackTextView: TextView

    private lateinit var ans1Btn: Button
    private lateinit var ans2Btn: Button
    private lateinit var ans3Btn: Button

    private var correctAnswer: Double = 0.0

    val ansArray = ArrayList<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_division, container, false)

        number1TextView = rootView.findViewById(R.id.number1TextView)
        number2TextView = rootView.findViewById(R.id.number2TextView)
        resultTextView = rootView.findViewById(R.id.resultTextView)
        feedbackTextView = rootView.findViewById(R.id.feedbackTextView)

        ans1Btn = rootView.findViewById(R.id.ans1)
        ans2Btn = rootView.findViewById(R.id.ans2)
        ans3Btn = rootView.findViewById(R.id.ans3)

        generateQuestion()

        ans1Btn.setOnClickListener{
            checkAnswer(ansArray[0])
        }

        ans2Btn.setOnClickListener{
            checkAnswer(ansArray[1])
        }

        ans3Btn.setOnClickListener{
            checkAnswer(ansArray[2])
        }

        return rootView
    }

    private fun generateQuestion(){
        var num1 = 0
        var num2 = 0
        correctAnswer = 0.0
        feedbackTextView.text = ""

        num1 = Random.nextInt(1, 5)
        num2 = Random.nextInt(1, 5)

        // Calculate the result with two decimal places
        val result = (num1.toDouble() / num2.toDouble())

        val roundedResult = String.format("%.1f", result).toDouble()
        correctAnswer = roundedResult

        number1TextView.text = num1.toString()
        number2TextView.text = num2.toString()

        ansArray.clear()
        ansArray.add(correctAnswer)
        ansArray.add(String.format("%.1f", correctAnswer + 1).toDouble())
        ansArray.add(String.format("%.1f", correctAnswer -1).toDouble())

        ansArray.shuffle()

        ans1Btn.text = ansArray[0].toString()
        ans2Btn.text = ansArray[1].toString()
        ans3Btn.text = ansArray[2].toString()

    }

    private fun checkAnswer(answer: Double) {
        if (answer == correctAnswer) {
            feedbackTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.correct_answer))
            resultTextView.text = correctAnswer.toString()
            feedbackTextView.text = "Correct Answer!"
            Handler().postDelayed({
                generateQuestion()
            }, 2000) // 2000 milliseconds is 2 seconds

        }else{
            feedbackTextView.setTextColor(Color.RED)
            feedbackTextView.text = "Wrong Answer. Try again!"
        }
    }
}