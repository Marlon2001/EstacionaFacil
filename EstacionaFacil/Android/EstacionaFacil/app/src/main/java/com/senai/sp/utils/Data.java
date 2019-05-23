package com.senai.sp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Data {

    public static String converterParaPortugues(String dt) {
        SimpleDateFormat stringParaDate = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        try {
            Date date = stringParaDate.parse(dt);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy kk:mm");
            return df.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
