package it.simonelambiase.StopGttService.service.model.mato.response.stop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopRoutesData {

    private Stop stop;
    public StopRoutesData() {

    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }
}
