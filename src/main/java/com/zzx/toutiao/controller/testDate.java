package com.zzx.toutiao.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testDate {
    public static void main(String  []args){
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        String timeFormat = sdf.format(time);
        System.out.println(timeFormat);

    }
}
