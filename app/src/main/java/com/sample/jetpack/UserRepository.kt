package com.sample.jetpack

class UserRepository {
    suspend fun getUser(name: String) : User {
        return UserApi.getUser(name)
    }
}