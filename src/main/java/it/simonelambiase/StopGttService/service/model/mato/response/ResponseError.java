package it.simonelambiase.StopGttService.service.model.mato.response;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError implements Serializable {

    private String errorMessage;

    private String date;


    public ResponseError() {}

    public ResponseError( String errorMessage, Exception e ) {
        this.errorMessage = errorMessage;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
