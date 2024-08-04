package com.organeco.view.customview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import com.organeco.view.utils.Utils.validEmailChecker

class EmailCustom : AppCompatEditText, View.OnTouchListener {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        setOnTouchListener(this)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(word: CharSequence, start: Int, before: Int, count: Int) {
                if (word.count() > 1) {
                    if (word.validEmailChecker()) {
                        Log.d("edit", "safe")
                    } else {
                        error = "email not valid"
                    }
                }
            }

            override fun afterTextChanged(s: Editable) {
            }
        })

    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        return false
    }
}