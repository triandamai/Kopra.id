package com.trian.common.utils.utils;

import android.annotation.SuppressLint;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
/**
 * XAxis value formatter
 * Author PT Cexup Telemedicine
 * Created by Trian Damai
 * 28/08/2021
 */


public class XAxisTimeFormatter extends ValueFormatter {
    private List<String> getCurrent = new  ArrayList<String>();

    public XAxisTimeFormatter(List<String> data){
        this.getCurrent.addAll(data);
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis){
        if(getCurrent.size() <= (int)value) return "";
       return getCurrent.get((int)value);
    }
}
