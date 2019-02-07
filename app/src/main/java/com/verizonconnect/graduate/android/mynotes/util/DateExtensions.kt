package com.verizonconnect.graduate.android.mynotes.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.getFormattedString(): String = SimpleDateFormat("d MMM, hh:mm", Locale.US).format(this)