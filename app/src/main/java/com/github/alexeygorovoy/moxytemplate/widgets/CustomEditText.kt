package com.github.alexeygorovoy.moxytemplate.widgets

import android.content.Context
import android.content.res.TypedArray
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.github.alexeygorovoy.moxytemplate.R
import com.github.alexeygorovoy.moxytemplate.utils.getColorCompat

class CustomEditText
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private lateinit var titleView: TextView
    private lateinit var editText: EditText
    private lateinit var errorMessageView: TextView
    private lateinit var lineView: View

    private val defaultTextColor = context.getColorCompat(R.color.black_70)
    private val defaultLineColor = context.getColorCompat(R.color.black_80)
    private val errorColor = context.getColorCompat(R.color.error)

    private var isError = false

    init {
        initLayout()
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)
        initWithAttributes(typedArray)
        typedArray.recycle()
    }

    private fun initLayout() {
        LayoutInflater.from(context).inflate(R.layout.custom_edit_text, this)
        orientation = VERTICAL

        titleView = findViewById(R.id.title)
        editText = findViewById(R.id.text)
        errorMessageView = findViewById(R.id.errorMessage)
        lineView = findViewById(R.id.line)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isError) {
                    hideError()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun initWithAttributes(typedArray: TypedArray) {
        val title = typedArray.getString(R.styleable.CustomEditText_title)
        val text = typedArray.getString(R.styleable.CustomEditText_text)
        val hint = typedArray.getString(R.styleable.CustomEditText_hint)
        val errorMessage = typedArray.getString(R.styleable.CustomEditText_errorMessage)
        val inputType = typedArray.getInt(R.styleable.CustomEditText_android_inputType, EditorInfo.TYPE_NULL)

        setTitle(title)
        setHint(hint)
        setErrorMessage(errorMessage)
        setText(text)
        editText.inputType = inputType
    }




    fun setHint(hint: CharSequence?) {
        editText.hint = hint
    }

    fun setTitle(title: CharSequence?) {
        titleView.text = title
    }

    fun setErrorMessage(error: CharSequence?) {
        errorMessageView.text = error
    }

    fun setText(text: CharSequence?) {
        editText.setText(text)
    }

    fun showError() {
        isError = true
        errorMessageView.visibility = View.VISIBLE
        lineView.setBackgroundColor(errorColor)
        editText.setTextColor(errorColor)
    }

    fun hideError() {
        isError = false
        lineView.setBackgroundColor(defaultLineColor)
        editText.setTextColor(defaultTextColor)
        errorMessageView.visibility = View.INVISIBLE
    }
}