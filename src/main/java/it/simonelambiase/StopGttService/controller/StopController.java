package it.simonelambiase.StopGttService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.simonelambiase.StopGttService.exception.MissingArgumentException;
import it.simonelambiase.StopGttService.model.ResponseModel;
import it.simonelambiase.StopGttService.service.MatoService;
import it.simonelambiase.StopGttService.service.model.mato.response.ResponseError;
import it.simonelambiase.StopGttService.service.model.mato.response.passage.BusPassageDTO;
import it.simonelambiase.StopGttService.utils.ErrorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static it.simonelambiase.StopGttService.Constants.*;
import static it.simonelambiase.StopGttService.utils.TimeUtils.calculateRemainingHoursInDay;

@RestController
@RequestMapping("/api/stop")
@CrossOrigin(origins = "*")
public class StopController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MatoService matoService;

    @GetMapping
    public ResponseEntity<ResponseModel> retrievePassagesByStopAndLine (@RequestParam(required = true) String stopNumber, @RequestParam(required = false) String lineNumber, @RequestParam(required = false) String hours ) {
        List<BusPassageDTO> busPassageList;
        try {
            if ( stopNumber == null || stopNumber.isEmpty() ) {
                throw new MissingArgumentException(MISSING_ARGUMENT);
            }
            busPassageList = matoService.retrieveBusPassage(stopNumber, hours == null ? calculateRemainingHoursInDay() : Integer.valueOf(hours)).stream().map(BusPassageDTO::new).toList();
            if (lineNumber != null && !lineNumber.isEmpty()) {
                busPassageList = busPassageList.stream().filter(busPassageDTO -> busPassageDTO.getLine().equals(lineNumber)).collect(Collectors.toList());
            }
        } catch ( Exception e ) {
            ResponseError responseError = ErrorUtils.retrieveResponseError(e);
            ResponseModel responseModel = new ResponseModel(true, responseError);
            return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ResponseModel responseModel = new ResponseModel(busPassageList);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }







}
