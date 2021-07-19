package com.programmersbox.quiz

import android.content.Intent
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.programmersbox.quizextras.Answer
import com.programmersbox.quizextras.Question
import com.programmersbox.quizextras.QuizActivity
import com.programmersbox.quizextras.QuizModel

class MainActivity : QuizActivity<String, String>() {

    override fun createQuiz(): List<QuizModel<String, String>> = listOf(
        QuizModel("1", "2", listOf("1", "2", "3")),
        QuizModel("2", "2", listOf("1", "2", "3")),
        QuizModel("3", "2", listOf("1", "2", "3"))
    )

    override fun answered(question: Question<String>, correctAnswer: Answer<String>, chosenAnswer: Answer<String>, count: Int) {

        if (correctAnswer == chosenAnswer) next()
        else Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()

        if (correctAnswer == chosenAnswer && count == questionList.lastIndex) {
            MaterialAlertDialogBuilder(this)
                .setMessage("Are you done?")
                .setPositiveButton("Yes") { d, _ ->
                    startActivity(Intent(this, SystemColorActivity::class.java))
                    d.dismiss()
                }
                .setNegativeButton("No") { d, _ -> d.dismiss() }
                .show()
        }
    }

}