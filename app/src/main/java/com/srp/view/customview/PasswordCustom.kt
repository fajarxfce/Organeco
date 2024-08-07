package com.srp.view.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.srp.R

class PasswordCustom : AppCompatEditText, View.OnTouchListener {
    private lateinit var iconTextImage: Drawable
    private lateinit var onItemHide: SetHideCallBack

    fun onItemClickDetail(onItemHide: SetHideCallBack) {
        this.onItemHide = onItemHide
    }


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
        iconTextImage = ContextCompat.getDrawable(context, R.drawable.ic_hidepass) as Drawable
        setOnTouchListener(this)
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(word: CharSequence, start: Int, before: Int, count: Int) {
                showEditIcon()
                if (word.length in 1..5) {
                    warnText()
                }
            }


            override fun afterTextChanged(s: Editable) {
            }
        })

    }

    private fun showEditIcon() {
        setButtonDrawables(
            endOfTheText = iconTextImage,
            startOfTheText = ContextCompat.getDrawable(
                context,
                R.drawable.ic_baseline_lock_24
            ) as Drawable
        )
    }

    private fun warnText() {
        error = invalid_pass
        iconTextImage = ContextCompat.getDrawable(context, R.drawable.ic_hidepass) as Drawable
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val iconTextStart: Float
            val iconTextEnd: Float
            var isClearButtonClicked = false

            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
                iconTextEnd = (iconTextImage.intrinsicWidth + paddingStart).toFloat()
                when {
                    event.x < iconTextEnd -> isClearButtonClicked = true
                }
            } else {
                iconTextStart = (width - paddingEnd - iconTextImage.intrinsicWidth).toFloat()
                when {
                    event.x > iconTextStart -> isClearButtonClicked = true
                }
            }
            if (isClearButtonClicked) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        iconTextImage = ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_showpass
                        ) as Drawable
                        onItemHide.setHideCallback(false)
                        return true
                    }
                    MotionEvent.ACTION_UP -> {
                        iconTextImage =
                            ContextCompat.getDrawable(context, R.drawable.ic_hidepass) as Drawable
                        onItemHide.setHideCallback(true)

                        return true
                    }
                    else -> return false
                }
            } else return false
        }
        return false
    }


    interface SetHideCallBack {
        fun setHideCallback(status: Boolean)
    }

    companion object {
        const val invalid_pass = "password must 6 char"
    }


}