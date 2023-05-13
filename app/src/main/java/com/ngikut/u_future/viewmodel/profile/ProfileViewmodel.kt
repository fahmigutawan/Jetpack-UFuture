package com.ngikut.u_future.viewmodel.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewmodel @Inject constructor(
    val repository: Repository
) : ViewModel(){
    fun setToken(){
        viewModelScope.launch {
            repository.setToken("")
        }
    }
}