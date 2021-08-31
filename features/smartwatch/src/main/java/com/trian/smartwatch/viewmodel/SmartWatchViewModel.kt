package com.trian.smartwatch.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.ICexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val cexupRepository: ICexupRepository
) :ViewModel(){
}