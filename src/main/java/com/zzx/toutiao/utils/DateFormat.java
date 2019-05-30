package com.zzx.toutiao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    //用于日期转换
    private Date targetDate;
    public DateFormat(Date date){
         this.targetDate = date;
    }
    public String getDateString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd hh:mm:ss");
        String timeFormat = sdf.format(this.targetDate);
        return timeFormat;
    }

}
