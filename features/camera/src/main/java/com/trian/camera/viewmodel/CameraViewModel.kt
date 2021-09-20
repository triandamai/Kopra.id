package com.trian.camera.viewmodel

import androidx.lifecycle.ViewModel
import com.trian.data.repository.IMeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(private val measurementRepository: IMeasurementRepository):ViewModel() {
}