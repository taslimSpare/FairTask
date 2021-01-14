package com.fairtask.models

data class UserResponse (
    var total: Int = 0,
    var page: Int = 0,
    var limit: Int = 0,
    var offset: Int = 0,
    var data: List<User> = mutableListOf()
)