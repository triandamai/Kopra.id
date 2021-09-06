package com.trian.smartwatch.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.ICexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
/**
 * Smartwatch ViewModel Class
 * Author PT Cexup Telemedhicine
 * Created by Trian Damai
 * 01/09/2021
 */
@HiltViewModel
class SmartWatchViewModel @Inject constructor(
    private val cexupRepository: ICexupRepository
) :ViewModel(){
}