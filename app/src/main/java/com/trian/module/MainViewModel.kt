package com.trian.module

import androidx.lifecycle.ViewModel
import com.trian.data.repository.CexupRepository
import com.trian.data.repository.ICexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cexupRepository: ICexupRepository
) :ViewModel(){

}