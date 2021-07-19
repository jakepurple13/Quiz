package com.programmersbox.quizextras

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView
import com.programmersbox.quizextras.databinding.ActivityQuizBinding
import com.programmersbox.quizextras.databinding.AnswerViewBinding

abstract class QuizActivity<Q, A> : AppCompatActivity(), QuestionAnswerListener<Q, A> {

    private val binding: ActivityQuizBinding by lazy { ActivityQuizBinding.inflate(layoutInflater) }

    protected val questionList: List<QuizModel<Q, A>> by lazy { createQuiz() }

    private var count = -1
        @SuppressLint("SetTextI18n")
        set(value) {
            field = when {
                value < 0 -> 0
                value >= questionList.size -> questionList.lastIndex
                else -> value
            }
            binding.counter.text = "${field + 1}/${questionList.size}"
        }

    private val adapter by lazy { QuestionAdapter() }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.questionRV.adapter = adapter
        binding.prevButton.setOnClickListener { previous() }
        binding.nextButton.setOnClickListener { next() }
        next()
    }

    protected fun next() {
        count++
        adapter.clear()
        adapter.add(questionList[count].possibleAnswers)
        binding.question = questionList[count].question
        binding.executePendingBindings()
    }

    protected fun previous() {
        count--
        adapter.clear()
        adapter.add(questionList[count].possibleAnswers)
        binding.question = questionList[count].question
        binding.executePendingBindings()
    }

    abstract fun createQuiz(): List<QuizModel<Q, A>>

    open fun createAnswerView(parent: ViewGroup, viewType: Int): AnswerViewHolder<A> =
        AnswerHolder(AnswerViewBinding.inflate(LayoutInflater.from(this@QuizActivity), parent, false))

    internal inner class QuestionAdapter(private val list: MutableList<Answer<A>> = mutableListOf()) : RecyclerView.Adapter<AnswerViewHolder<A>>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder<A> = createAnswerView(parent, viewType)
        override fun onBindViewHolder(holder: AnswerViewHolder<A>, position: Int) = holder.bind(list[position])
        override fun getItemCount(): Int = list.size

        fun clear() = list.clear()
        fun add(items: List<Answer<A>>) {
            list.addAll(items)
            notifyDataSetChanged()
        }
    }

    internal inner class AnswerHolder(private val binding: AnswerViewBinding) : AnswerViewHolder<A>(binding.root) {
        override fun bind(answer: Answer<A>) {
            binding.answer = answer
            binding.executePendingBindings()
            binding.root.setOnClickListener { answered(questionList[count].question, questionList[count].correctAnswer, answer, count) }
        }
    }

}

abstract class AnswerViewHolder<A>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(answer: Answer<A>)
}

interface QuestionAnswerListener<Q, A> {
    fun answered(question: Question<Q>, correctAnswer: Answer<A>, chosenAnswer: Answer<A>, count: Int)
}