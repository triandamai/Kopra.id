package com.trian.module.common

import android.content.Context
import com.trian.common.R
import com.trian.common.utils.analytics.*

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

import org.mockito.runners.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AnalyticsTest {
    @Mock
    private lateinit var mockContext: Context

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should success analyze bpm`(){
        doReturn("Optimal")
            .`when`(mockContext)
            .getString(R.string.optimal)
        doReturn("Normal")
            .`when`(mockContext)
            .getString(R.string.normal)
        doReturn("Normal High")
            .`when`(mockContext)
            .getString(R.string.normal_high)
        doReturn("Hypertension Grade 1")
            .`when`(mockContext)
            .getString(R.string.hypertension_grade_1)
        doReturn("Hypertension Grade 2")
            .`when`(mockContext)
            .getString(R.string.hypertension_grade_2)
        doReturn("Hypertension Grade 3")
            .`when`(mockContext)
            .getString(R.string.hypertension_grade_3)

        val resultOptimal = analyzeBPM(119,60)
        val resultNormal = analyzeBPM(124,82)
        val resultNormalHigh = analyzeBPM(131,86)
        val resultHypertensionGrade1 = analyzeBPM(144,99)
        val resultHypertensionGrade2 = analyzeBPM(165,105)
        val resultHypertensionGrade3 = analyzeBPM(180,110)
       assertEquals("Optimal",mockContext.getString(resultOptimal.result))
       assertEquals("Normal",mockContext.getString(resultNormal.result))
       assertEquals("Normal High",mockContext.getString(resultNormalHigh.result))
       assertEquals("Hypertension Grade 1",mockContext.getString(resultHypertensionGrade1.result))
       assertEquals("Hypertension Grade 2",mockContext.getString(resultHypertensionGrade2.result))
       assertEquals("Hypertension Grade 3",mockContext.getString(resultHypertensionGrade3.result))
    }

    @Test
    fun `should analyze temperature`(){
        doReturn("Hypothermia")
            .`when`(mockContext)
            .getString(R.string.hypothermia)
        doReturn("Normal")
            .`when`(mockContext)
            .getString(R.string.normal)
        doReturn("Hyperthermia")
            .`when`(mockContext)
            .getString(R.string.hyperthermia)
        doReturn("Hyperpyrexia")
            .`when`(mockContext)
            .getString(R.string.hyperpyrexia)
        doReturn("Undefined")
            .`when`(mockContext)
            .getString(R.string.undefined)

        val resultHypothermia = (32f).analyzeTemperature()
        val resultNormal = (36f).analyzeTemperature()
        val resultHyperthermia = (39f).analyzeTemperature()
        val resultHyperpyrexia = (41f).analyzeTemperature()
        val resultUndefined = (99f).analyzeTemperature()
        val resultUndefined2 = (0f).analyzeTemperature()

        assertEquals("Hypothermia",mockContext.getString(resultHypothermia.result))
        assertEquals("Normal",mockContext.getString(resultNormal.result))
        assertEquals("Hyperthermia",mockContext.getString(resultHyperthermia.result))
        assertEquals("Hyperpyrexia",mockContext.getString(resultHyperpyrexia.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined2.result))
    }

    @Test
    fun `should analyze heartrate`(){
        doReturn("Bradycardia")
            .`when`(mockContext)
            .getString(R.string.bradycardia)
        doReturn("Normal")
            .`when`(mockContext)
            .getString(R.string.normal)
        doReturn("Tachycardia")
            .`when`(mockContext)
            .getString(R.string.tachycardia)
        doReturn("Undefined")
            .`when`(mockContext)
            .getString(R.string.undefined)

        val resultBradycardia = (49f).analyzeHeartRate()
        val resultNormal = (52f).analyzeHeartRate()
        val resultTachycardia = (101f).analyzeHeartRate()
        val resultUndefined = (0f).analyzeHeartRate()

        assertEquals("Bradycardia",mockContext.getString(resultBradycardia.result))
        assertEquals("Normal",mockContext.getString(resultNormal.result))
        assertEquals("Tachycardia",mockContext.getString(resultTachycardia.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined.result))
    }

    @Test
    fun `should analyze respiratory`(){
        doReturn("Bradypnea")
            .`when`(mockContext)
            .getString(R.string.bradypnea)
        doReturn("Normal")
            .`when`(mockContext)
            .getString(R.string.normal)
        doReturn("Tachypnea")
            .`when`(mockContext)
            .getString(R.string.tachypnea)
        doReturn("Undefined")
            .`when`(mockContext)
            .getString(R.string.undefined)

        val resultBradypnea = (10f).analyzeRespiratory()
        val resultNormal = (15f).analyzeRespiratory()
        val resultTachypnea = (21f).analyzeRespiratory()
        val resultUndefined = (0f).analyzeRespiratory()
        assertEquals("Bradypnea",mockContext.getString(resultBradypnea.result))
        assertEquals("Normal",mockContext.getString(resultNormal.result))
        assertEquals("Tachypnea",mockContext.getString(resultTachypnea.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined.result))
    }
    @Test
    fun `should analyze spo2`(){
        doReturn("Severe Hypoxia")
            .`when`(mockContext)
            .getString(R.string.severe_hypoxia)
        doReturn("Moderate Hypoxia")
            .`when`(mockContext)
            .getString(R.string.moderate_hypoxia)
        doReturn("Mild Hypoxia")
            .`when`(mockContext)
            .getString(R.string.mild_hypoxia)
        doReturn("Normal Saturation")
            .`when`(mockContext)
            .getString(R.string.normal_saturation)
        doReturn("Undefined")
            .`when`(mockContext)
            .getString(R.string.undefined)

        val resultSevereHypoxia = (84f).analyzeSpo2()
        val resultModerateHypoxia = (87f).analyzeSpo2()
        val resultMildHypoxia = (93f).analyzeSpo2()
        val resultNormalSaturation = (96f).analyzeSpo2()
        val resultUndefined = (0f).analyzeSpo2()
        assertEquals("Severe Hypoxia",mockContext.getString(resultSevereHypoxia.result))
        assertEquals("Moderate Hypoxia",mockContext.getString(resultModerateHypoxia.result))
        assertEquals("Mild Hypoxia",mockContext.getString(resultMildHypoxia.result))
        assertEquals("Normal Saturation",mockContext.getString(resultNormalSaturation.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined.result))
    }

    @Test
    fun `should analyze bmi`(){
        doReturn("UnderWeight")
            .`when`(mockContext)
            .getString(R.string.underweight)
        doReturn("Normal")
            .`when`(mockContext)
            .getString(R.string.normal)
        doReturn("OverWeight")
            .`when`(mockContext)
            .getString(R.string.overweight)
        doReturn("Obesity Grade 1")
            .`when`(mockContext)
            .getString(R.string.obesity_grade_1)
        doReturn("Obesity Grade 3")
            .`when`(mockContext)
            .getString(R.string.obesity_grade_3)
        doReturn("Undefined")
            .`when`(mockContext)
            .getString(R.string.undefined)

        val resultUnderWeight = (18f).analyzBmi()
        val resultNormal = (20f).analyzBmi()
        val resultOverWeight = (26f).analyzBmi()
        val resultObesityGrade1 = (31f).analyzBmi()
        val resultObesityGrade3 = (42f).analyzBmi()
        val resultUndefined = (0f).analyzBmi()
        assertEquals("UnderWeight",mockContext.getString(resultUnderWeight.result))
        assertEquals("Normal",mockContext.getString(resultNormal.result))
        assertEquals("OverWeight",mockContext.getString(resultOverWeight.result))
        assertEquals("Obesity Grade 1",mockContext.getString(resultObesityGrade1.result))
        assertEquals("Obesity Grade 3",mockContext.getString(resultObesityGrade3.result))
        assertNotEquals("Undefined",mockContext.getString(resultUndefined.result))
    }
}