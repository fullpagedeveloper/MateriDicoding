package com.fullpagedeveloper.edittextgue

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class EditTextGue: LinearLayout {

    private var mSpace = 24f
    private var mCharSize = 0f
    private var mNumberCharacter = 4f


    var entryCount = 0
    private var currentIndex = 0
    private val EDITTEX_MAX_LENGTH = 1
    private val EDITTEXT_WIDTH = 40
    private val EDITTEXT_TEXTSIZE = 20
    private var disableTextWacher = false
    private var backKeySet = false
    private var textWatcher: TextWatcher? = null
    //private var mListener: onF
    //private val mListerner: onFinishListerner? = null


    constructor(context: Context): super(context){
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {

        val a = context.obtainStyledAttributes(attrs, R.styleable.EditTextGue, 0,0)

        entryCount = a.getInteger(R.styleable.EditTextGue_entryCount, 0)
        a.recycle()

        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL

        for (i in 0 until  entryCount) {
            addView(initialiseAndAddChildInLayout(i, context), i)
        }

        //setBackgroundResource(0)

        //val multi = context.resources.displayMetrics.density
       // mSpace *= multi //dirubah menjadi pixel kepadatannya
    }

    //method focuses of previous editext
    private fun getPreviousEditext(index: Int) {
        if (index > 0) {
            val edtxt = getChildAt(index - 1) as EditText
            disableTextWatcher = true
            edtxt.setText("")
            edtxt.requestFocus()
            disableTextWatcher = false
        }
    }

    //method focuses of previous editext
    private fun getPreviousEditextFocus(index: Int) {
        if (index > 0) {
            val edtxt = getChildAt(index - 1) as EditText
            disableTextWatcher = true
            edtxt.requestFocus()
            disableTextWatcher = false
        }
    }

    //method to focus on next edittext
    private fun getNextEditext(index: Int) {
        if (index < entryCount - 1) {
            val edtxt = getChildAt(index + 1) as EditText
            edtxt.requestFocus()
        }
    }


    private fun initialiseAndAddChildInLayout(
        index: Int,
        context: Context
    ): View? {
        val editext = EditText(context)
        editext.maxWidth = 1
        editext.tag = index
        editext.gravity = Gravity.CENTER
        editext.textSize = EDITTEXT_TEXTSIZE.toFloat()
        editext.inputType = EditorInfo.TYPE_CLASS_NUMBER
        editext.filters = arrayOf<InputFilter>(LengthFilter(EDITTEXT_MAX_LENGTH))
        val param =
            LayoutParams(0, LayoutParams.WRAP_CONTENT, 1)
        editext.layoutParams = param
        editext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                currentIndex = editext.tag.toString().toInt()
                if (editext.text.toString().length == 1 && !disableTextWatcher) {
                    getNextEditext(currentIndex)
                } else if (editext.text
                        .toString().length == 0 && !disableTextWatcher
                ) { // && !isFirstTimeGetFocused && !backKeySet) {
                    getPreviousEditext(currentIndex)
                }
            }

            override fun afterTextChanged(s: Editable) {}
        }.also { txtWatcher = it })
        editext.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL) {
                currentIndex = editext.tag.toString().toInt()
                if (editext.text.toString().length == 0 && !disableTextWatcher) {
                    getPreviousEditextFocus(currentIndex)
                } else {
                    disableTextWatcher = true
                    editext.setText("")
                    disableTextWatcher = false
                }
                backKeySet = true
            }
            true
        }
        editext.setOnEditorActionListener { v, actionId, event ->
            if (event != null && event.keyCode === KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                if (currentIndex == entryCount - 1 && getEnteredText().length == entryCount) {
                    mListerner.onFinish(getEnteredText())
                }
            }
            false
        }
        return editext
    }


    fun getEnteredText(): String {
        var strEnteredValue = ""
        for (i in 0 until childCount) {
            val editText = getChildAt(i) as EditText
            if (editText.text != null && editText.text
                    .toString().length > 0
            ) strEnteredValue = strEnteredValue + editText.text.toString()
        }
        return strEnteredValue
    }

    fun clearCustomEntryEdittext() {
        for (i in 0 until childCount) {
            val editText = getChildAt(i) as EditText
            editText.setText("")
        }
        val editText = getChildAt(0) as EditText
        editText.requestFocus()
    }

    /*override fun onDraw(canvas: Canvas) {
        //super.onDraw(canvas)
        val availableWidth = width - paddingRight - paddingLeft

        mCharSize = if (mSpace < 0) {
            (availableWidth / (mNumberCharacter * 2 - 1))
        } else {
            (mSpace * (mNumberCharacter - 1)) / mNumberCharacter
        }

        var startX = paddingStart
        val bottom = height  - paddingBottom

        for (i in 0 until mNumberCharacter.toInt()) {
            canvas.drawLine(startX.toFloat(), bottom.toFloat(), startX + mCharSize, bottom.toFloat(), paint)
            startX += if (mSpace < 0) {
                (mCharSize * 2).toInt()
            } else {
                (mCharSize + mSpace).toInt()
            }
        }
    }*/
}