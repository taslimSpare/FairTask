package com.fairtask.fn

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.fairtask.models.User
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson


const val preferenceKey = "x_pref_key"


fun Context.toast(m: String?) =
    Toast.makeText(this, m, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.BOTTOM, 0, 0)
    }.show()


fun Activity.snack(message: String) =
    Snackbar.make(findViewById(android.R.id.content),
        message, Snackbar.LENGTH_SHORT).show()

fun User.saveProfile(context: Context) {
    val pref = context.getSharedPreferences("CONSTANT", Context.MODE_PRIVATE)
    val s = Gson().toJson(this)
    pref.edit().putString(preferenceKey, s).apply()
}

fun getProfile(context: Context) : User{
    val pref = context.getSharedPreferences("CONSTANT", Context.MODE_PRIVATE)
    val s = pref.getString(preferenceKey, "") ?: ""
    return if (s == "") User() else Gson().fromJson(s, User::class.java)
}