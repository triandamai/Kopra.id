package com.trian.waist.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.ICexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class WaistViewModel(
    private val cexupRepository: ICexupRepository
) :ViewModel(){
}