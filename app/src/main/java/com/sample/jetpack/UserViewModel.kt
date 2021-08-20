package com.sample.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val userLiveData = MutableLiveData<User>()

    private val userRepository: UserRepository = UserRepository()

    fun getUser(name: String) {
        viewModelScope.launch {
            userLiveData.value = userRepository.getUser(name)
        }
    }
}