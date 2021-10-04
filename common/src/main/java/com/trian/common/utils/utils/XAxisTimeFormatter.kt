package com.trian.common.utils.utils;

import android.util.Log
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * XAxis value formatter
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
 class XAxisTimeFormatter : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return value.toString()
    }
}
