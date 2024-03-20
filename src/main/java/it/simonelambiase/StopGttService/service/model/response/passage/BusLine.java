package it.simonelambiase.StopGttService.service.model.response.passage;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class BusLine {

    @JsonProperty("pattern")
    private BusLinePattern busLinePattern;

    public BusLine() {
    }

    public String getShortName() {
       return busLinePattern.getBusLineData().getShortName();
    }

    public String getLongName() {
        return busLinePattern.getBusLineData().getLongName();
    }

}
