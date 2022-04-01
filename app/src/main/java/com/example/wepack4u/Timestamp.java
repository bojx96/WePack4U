package com.example.wepack4u;

import java.util.Calendar;

public class Timestamp {
    private final Calendar c;

    Timestamp() { c = Calendar.getInstance(); }

    public String setTimestamp() {
        String secondText = formatText(c.get(Calendar.SECOND));
        String minuteText = formatText(c.get(Calendar.MINUTE));
        return c.get(Calendar.HOUR_OF_DAY) + ":" + minuteText + ":" + secondText + " " +
                c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
    }

    private String formatText(int time) {
        String result;
        if (time < 10) { result = "0" + time; }
        else { result = "" + time; }
        return result;
    }
}
