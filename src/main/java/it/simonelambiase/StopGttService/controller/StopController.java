package it.simonelambiase.StopGttService.controller;

import it.simonelambiase.StopGttService.mato.MatoService;
import it.simonelambiase.StopGttService.mato.model.response.passage.BusPassage;
import it.simonelambiase.StopGttService.mato.model.response.passage.BusPassageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stop")
public class StopController {

    @Autowired
    private MatoService matoService;


    @GetMapping
    public List<BusPassageDTO> retrievePassagesByStopAndLine ( @RequestParam String stopNumber, @RequestParam(required = false) String lineNumber, @RequestParam(required = false) String hours ) {
        List<BusPassageDTO> busPassageList = matoService.retrieveBusPassage(stopNumber,hours == null ? 12 : Integer.valueOf(hours)).stream().map(BusPassageDTO::new).toList();
        if ( lineNumber != null && !lineNumber.isEmpty()) busPassageList = busPassageList.stream().filter(busPassageDTO -> busPassageDTO.getLine().equals(lineNumber)).collect(Collectors.toList());
        return busPassageList;
    }
}
