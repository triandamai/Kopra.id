package com.trian.data.utils;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * XAxis value formatter
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */
public class XAxisTimeFormatter extends ValueFormatter {
    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        Date date = new Date((long) value);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        return sdf.format(date);
    }
}
