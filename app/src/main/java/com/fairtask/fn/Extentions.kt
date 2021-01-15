package com.fairtask.fn

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar


fun Context.toast(m: String?) =
    Toast.makeText(this, m, Toast.LENGTH_LONG).apply {
        setGravity(Gravity.BOTTOM, 0, 0)
    }.show()

fun Context.showDialog(title: String, message: String) =
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
        .create().show()

fun Activity.snack(message: String) =
    Snackbar.make(findViewById(android.R.id.content),
        message, Snackbar.LENGTH_LONG).show()

