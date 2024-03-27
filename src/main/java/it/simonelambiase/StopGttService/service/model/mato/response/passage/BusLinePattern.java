package it.simonelambiase.StopGttService.service.model.mato.response.passage;

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
