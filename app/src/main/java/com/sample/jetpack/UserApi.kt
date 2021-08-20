package com.sample.jetpack

import kotlinx.coroutines.delay

object UserApi {
    suspend fun getUser(name: String): User {
        delay(1000)
        return User(name)
    }
}