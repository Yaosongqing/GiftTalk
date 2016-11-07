package com.test.yxj.gifttalk.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/7.
 */
public class DateTool {

    public static String formatDate(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 EE");
        return simpleDateFormat.format(new Date(time));
    }
}
