package it.simonelambiase.StopGttService.utils;

import java.time.LocalDateTime;

public class TimeUtils {

    public static int calculateRemainingHoursInDay() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentHour = localDateTime.getHour();
        return 24 - currentHour;
    }
}
