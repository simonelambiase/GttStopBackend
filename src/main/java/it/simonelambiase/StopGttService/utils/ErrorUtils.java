package it.simonelambiase.StopGttService.utils;

import it.simonelambiase.StopGttService.exception.MatoServiceException;
import it.simonelambiase.StopGttService.exception.MissingArgumentException;
import it.simonelambiase.StopGttService.service.model.mato.response.ResponseError;

import static it.simonelambiase.StopGttService.Constants.*;

public class ErrorUtils {

    public static ResponseError retrieveResponseError( Exception e ) {
        if ( e instanceof MissingArgumentException ) {
            return new ResponseError(MISSING_ARGUMENT,e);
        } else if ( e instanceof MatoServiceException ) {
            return new ResponseError(MATO_SERVICE_ERROR,e);
        } else {
            return new ResponseError(GENERIC_ERROR,e);
        }
    }
}
