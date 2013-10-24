package org.emamotor.javase.utility.timezone;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author emag
 */
public class TimeZoneTest {

    public static void main(String... args) {

        String[] timezones = {
                "Japan",
                "America/Los_Angeles",
                "America/New_York",
                "Greenwich",
                "Antarctica/South_Pole"
        };

        Date date = Calendar.getInstance().getTime();

        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        for (String eachTimezone : timezones) {
            TimeZone timeZone = TimeZone.getTimeZone(eachTimezone);
            format.setTimeZone(timeZone);
            System.out.print(timeZone.getDisplayName() + " ");
            System.out.println(format.format(date));
        }
    }
}
