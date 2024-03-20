package it.simonelambiase.StopGttService.service.model.response.stop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {

    private String id;

    public Stop() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
