package com.fairtask.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(
    @PrimaryKey val id: String = "",
    val title: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var picture: String = "",
    var email: String = "",
    var saved: Boolean = false
) {
    fun idExistentInList(list: MutableList<User>): Boolean {
        list.forEach { _user ->
            if(_user.id == id) return true
        }
        return false
    }
}
