package it.simonelambiase.StopGttService.model;

import java.io.Serializable;

public class ResponseModel implements Serializable {

    private boolean hasError;

    private Object responseBody;

    public ResponseModel() {}

    public ResponseModel(Object responseBody) {
        this.responseBody = responseBody;
        this.hasError = false;
    }

    public ResponseModel(boolean hasError, Object responseBody) {
        this.hasError = hasError;
        this.responseBody = responseBody;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Object getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(Object responseBody) {
        this.responseBody = responseBody;
    }
}
