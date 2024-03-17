package it.simonelambiase.StopGttService.mato;

import it.simonelambiase.StopGttService.mato.model.response.passage.BusPassage;
import it.simonelambiase.StopGttService.mato.model.response.passage.BusPassageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatoServiceTest  {

    @Autowired
    private MatoService matoService;

    @Test
    void retrieveBusPassage() {
        List<BusPassage> busPassages = matoService.retrieveBusPassage("754",12);
        assertNotNull(busPassages);
        assertFalse(busPassages.isEmpty());
    }

    @Test
    void retrieveCodedId() {
       String codedId = matoService.retrieveCodedStopId("754");
       System.out.println("Coded id: " + codedId);
       assertNotNull(codedId);
       assertFalse(codedId.isEmpty());
    }

    @Test
    void retrieveBusPassageDTO() {
        List<BusPassage> busPassages = matoService.retrieveBusPassage("754",12);
        assertNotNull(busPassages);
        assertFalse(busPassages.isEmpty());
        List<BusPassageDTO> busPassageDTOList = busPassages.stream().map(BusPassageDTO::new).toList();
        assertNotNull(busPassageDTOList);
        assertFalse(busPassageDTOList.isEmpty());
    }

}