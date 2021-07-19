package com.programmersbox.quizextras

import java.io.Serializable

data class QuizModel<Q, A>(val question: Question<Q>, val correctAnswer: Answer<A>, val possibleAnswers: List<Answer<A>>) : Serializable {
    constructor(question: Q, correctAnswer: A, possibleAnswers: List<A>) : this(
        Question(question, question.toString()),
        Answer(correctAnswer, correctAnswer.toString()),
        possibleAnswers.map { Answer(it, it.toString()) }
    )
}

data class Question<Q>(val question: Q, val asString: String) : Serializable

data class Answer<A>(val answer: A, val asString: String) : Serializable