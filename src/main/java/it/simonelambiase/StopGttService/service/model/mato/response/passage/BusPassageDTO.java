package it.simonelambiase.StopGttService.service.model.mato.response.passage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class BusPassageDTO {

    private String line;

    private String hour;

    private String direction;

    private boolean realtime;


    public BusPassageDTO() {}

    public BusPassageDTO(String line, String hour, boolean realtime) {
        this.line = line;
        this.hour = hour;
        this.realtime = realtime;
    }

    public BusPassageDTO ( BusPassage busPassage ) {
        this.line = busPassage.getLine().getShortName();
        this.realtime = busPassage.isRealtime();
        this.hour = convertSecondToHour(busPassage.getRealtimeArrival());
        this.direction = busPassage.getStopHeadsign();
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    protected String convertSecondToHour(long arrivalTime ) {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        LocalDateTime result = now.plusSeconds(arrivalTime);
        return result.atZone(ZoneId.of("Europe/Rome")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
