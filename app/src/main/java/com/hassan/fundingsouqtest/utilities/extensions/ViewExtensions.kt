package com.hassan.fundingsouqtest.utilities.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.showSnackBar(text: String,  action: ()->Unit?,textToShow: String? = "Ok" ,duration: Int? = -1) {
    Snackbar.make(this, text, duration?: -1)
        .setAction(textToShow, View.OnClickListener {
            if (action != null) {
                action()
            }
        })
        .show()
}


fun Activity.showToast(message: String,  context: Context? = applicationContext, length: Int? = 0){
    Toast.makeText(context, message, length?: 0).show()
}

fun Context.goTo(clazz: Class<*>, extras: Bundle? = null){
    val intent = Intent(this,clazz)
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivity(intent)
}

fun Context.goToAndClearStack(clazz: Class<*>, extras: Bundle? = null) {
    val intent = Intent(this, clazz)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    if (extras != null) {
        intent.putExtras(extras)
    }
    startActivity(intent)
}