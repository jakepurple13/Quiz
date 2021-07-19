package com.programmersbox.quiz

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.card.MaterialCardView

class CustomCard : FrameLayout {

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.color_item, null)
        addView(v)

        attrs?.let {

            val a = context.obtainStyledAttributes(it, R.styleable.CustomCard)
            v.findViewById<MaterialCardView>(R.id.customCardLayout)
                ?.setCardBackgroundColor(a.getColor(R.styleable.CustomCard_customcard_cardBackgroundColor, Color.BLACK))
            v.findViewById<TextView>(R.id.customCardText)?.text = a.getString(R.styleable.CustomCard_customcard_text)
            a.recycle()

        }
    }

}