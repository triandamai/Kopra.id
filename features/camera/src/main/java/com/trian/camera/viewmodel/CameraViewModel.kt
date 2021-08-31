package com.trian.camera.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.ICexupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val cexupRepository: ICexupRepository):ViewModel() {
}