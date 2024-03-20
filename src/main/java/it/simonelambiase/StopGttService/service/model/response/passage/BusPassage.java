package it.simonelambiase.StopGttService.service.model.response.passage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusPassage {
    private String realtimeState;
    private long realtimeDeparture;
    private long scheduledDeparture;
    private long realtimeArrival;
    private long scheduledArrival;
    private boolean realtime;
    private long serviceDay;
    private String pickupType;
    private String stopHeadsign;

    @JsonProperty("trip")
    private BusLine line;

    // Costruttore vuoto
    public BusPassage() {
    }

    public String getRealtimeState() {
        return realtimeState;
    }

    public void setRealtimeState(String realtimeState) {
        this.realtimeState = realtimeState;
    }

    public long getRealtimeDeparture() {
        return realtimeDeparture;
    }

    public void setRealtimeDeparture(long realtimeDeparture) {
        this.realtimeDeparture = realtimeDeparture;
    }

    public long getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(long scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public long getRealtimeArrival() {
        return realtimeArrival;
    }

    public void setRealtimeArrival(long realtimeArrival) {
        this.realtimeArrival = realtimeArrival;
    }

    public long getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(long scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public long getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(long serviceDay) {
        this.serviceDay = serviceDay;
    }

    public String getPickupType() {
        return pickupType;
    }

    public void setPickupType(String pickupType) {
        this.pickupType = pickupType;
    }

    public String getStopHeadsign() {
        return stopHeadsign;
    }

    public void setStopHeadsign(String stopHeadsign) {
        this.stopHeadsign = stopHeadsign;
    }

    public BusLine getLine() {
        return line;
    }

    public void setLine(BusLine line) {
        this.line = line;
    }
}
