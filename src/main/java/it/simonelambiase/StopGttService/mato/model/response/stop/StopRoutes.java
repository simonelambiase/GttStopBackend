package it.simonelambiase.StopGttService.mato.model.response.stop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopRoutes {


    private StopRoutesData data;

    public StopRoutes() {

    }

    public StopRoutesData getData() {
        return data;
    }

    public void setData(StopRoutesData data) {
        this.data = data;
    }
}
