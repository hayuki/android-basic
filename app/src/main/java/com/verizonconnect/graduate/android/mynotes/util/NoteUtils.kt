package com.verizonconnect.graduate.android.mynotes.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

fun sortNotes(context: Context, uniqueID: String) {
    val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$uniqueID"))
    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$uniqueID"))
    try {
        context.startActivity(intentApp)
    } catch (ex: ActivityNotFoundException) {
        context.startActivity(intentBrowser)
    }
    
}