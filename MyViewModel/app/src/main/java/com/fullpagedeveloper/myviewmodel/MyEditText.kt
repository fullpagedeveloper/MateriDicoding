package com.fullpagedeveloper.myviewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginEnd

class MyEditText: AppCompatEditText {

    private lateinit var mClearButtonImage: Drawable

    constructor(context: Context): super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr){
        init()
    }

    //fungsi action
    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close, null) as Drawable
        setOnTouchListener(OnTouchListener { v, event ->
            //compoundDrawablesRelative[2] // ini untuk mengambil drawable di sebelah kanan
            //jd compoundDrawablesRelative itu nge return array isinya 4
            //0 = start
            //1 = top
            //2 = end
            //3 = bottom
            if (compoundDrawablesRelative[2] != null) {
                Log.d("tess", " ${compoundDrawablesRelative.size}")
                val clearButtonStart: Float
                val clearButtonEnd: Float
                var isClearButtonClicked = false
                //jika edittext nya di tap yang ada icon X maka clear button true, atau tanda X tampil
                //kedua fungsi ini - untuk menghapus/ membuat fungsi icon X clear
                when (layoutDirection) {
                    View.LAYOUT_DIRECTION_RTL -> {
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                        when {
                            event.x < clearButtonEnd -> isClearButtonClicked = true
                        }
                    }
                    else -> {
                        clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                        when {
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }

                //kedua fungsi ini - untuk menghapus/ membuat fungsi icon X clear
                //start
                when {
                    isClearButtonClicked -> when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close, null) as Drawable
                            showClearButton()
                            return@OnTouchListener true
                        }

                        MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_close, null) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return@OnTouchListener true
                        }
                        else -> return@OnTouchListener false
                    }
                    else -> return@OnTouchListener false
                }
                //end
            }
            false
        })

        //icon x tampil
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //ini fungsi otomatis menampilkan icon X
                when {
                    s.toString().isNotEmpty() -> showClearButton()
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }
    //end init()

    private fun showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)// Start of text.
    }
    private fun hideClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null)// Start of text.
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukan nama anda"
        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
    }
}