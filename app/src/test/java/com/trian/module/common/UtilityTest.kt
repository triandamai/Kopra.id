package com.trian.module.common

import com.trian.common.utils.utils.*
import org.junit.Assert.assertEquals
import org.junit.Test

class UtilityTest {
    @Test
    fun `get Next And Previous date from Timestamp`(){
        //1632889813000
        val currentDate = 1632889813000
        assertEquals("2021-09-29 11:30:13",currentDate.formatDate())
        assertEquals("2021-09-28 00:00:00",currentDate.getPreviousDate().formatDate())
        assertEquals("2021-09-30 00:00:00",currentDate.getNextDate().formatDate())
        assertEquals("2021-09-29 11:30:13",currentDate.formatDate())
        assertEquals("September 29, 2021",currentDate.formatReadableDate())
    }
}