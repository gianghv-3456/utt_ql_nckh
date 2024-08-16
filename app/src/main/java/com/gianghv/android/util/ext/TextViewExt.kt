package com.gianghv.android.util.ext

import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import java.net.URL
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

fun TextView.setHyperLink(content: String, url: String) {
    this.movementMethod = LinkMovementMethod.getInstance()
    val text = "<a href='$url'>$content</a>"
    this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
}
