package com.gianghv.android.util.ext

import android.annotation.SuppressLint
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.gianghv.android.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun TextView.dateFormatter(string: String?) {
    if (string?.isNotEmpty() == true) {
        val date = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(string)
        date?.let {
            val format = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
            this.text = format.format(it)
        }
    }
}

fun TextView.showDateDMY(date: Date) {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    this.text = format.format(date)
}

@SuppressLint("ObsoleteSdkInt")
fun TextView.setHyperLink(content: String, url: String) {
    val text = "<a href='$url'>$content</a>"
    this.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    } else {
        Html.fromHtml(text)
    }
    this.setTextColor(this.context.getColor(R.color.primary))
    this.movementMethod = LinkMovementMethod.getInstance()
}
