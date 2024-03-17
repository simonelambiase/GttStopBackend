package it.simonelambiase.StopGttService.mato.model.response.passage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusLinePattern {

    @JsonProperty("route")
    private BusLineData busLineData;

    public BusLinePattern() {
    }

    public BusLineData getBusLineData() {
        return busLineData;
    }

    public void setBusLineData(BusLineData busLineData) {
        this.busLineData = busLineData;
    }
}
