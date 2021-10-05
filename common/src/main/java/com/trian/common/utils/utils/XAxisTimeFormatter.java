package com.trian.common.utils.utils;

import android.annotation.SuppressLint;

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

@SuppressLint("NewApi")
 public class XAxisTimeFormatter extends ValueFormatter {

    private List<String> listDate = new ArrayList<>();

    public XAxisTimeFormatter(List<String> date){
            listDate.addAll(date);
    }

    @Override
    public String getFormattedValue(float value) {
        return listDate.get(Float.valueOf(value).intValue());
    }
}
