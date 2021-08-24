package com.trian.module

import androidx.lifecycle.ViewModel
import com.trian.data.repository.CexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cexupRepository: CexupRepository
) :ViewModel(){
}