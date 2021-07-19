package com.programmersbox.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.programmersbox.quiz.databinding.ActivitySystemColorBinding
import com.programmersbox.quiz.databinding.ColorItemBinding

class SystemColorActivity : AppCompatActivity() {

    //private val binding: ActivitySystemColorBinding by lazy { ActivitySystemColorBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_color)
    }

}