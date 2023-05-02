package com.ngikut.u_future.viewmodel.ubot

import androidx.lifecycle.ViewModel
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UbotViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel(){
}