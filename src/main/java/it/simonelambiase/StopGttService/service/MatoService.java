package it.simonelambiase.StopGttService.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.simonelambiase.StopGttService.exception.MatoServiceException;
import it.simonelambiase.StopGttService.service.model.mato.response.stop.StopRoutes;
import it.simonelambiase.StopGttService.service.model.mato.response.passage.BusPassage;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static it.simonelambiase.StopGttService.Constants.MATO_SERVICE_ERROR;

/*
Implementazione della chiamata al servizio GraphQL di Muoversi a Torino
 */

@Service
public class MatoService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String matoEndpoint = "https://plan.muoversiatorino.it/otp/routers/mato/index/graphql";
    private final String codedIdQuery = "{\"id\":\"q01\",\"query\":\"query StopRoutes($id_0:String!,$startTime_1:Long!,$timeRange_2:Int!,$numberOfDepartures_3:Int!) {stop(id:$id_0) {id,...F2}} fragment F0 on Alert {id,alertDescriptionText,alertHash,alertHeaderText,alertSeverityLevel,alertUrl,effectiveEndDate,effectiveStartDate,alertDescriptionTextTranslations {language,text},alertHeaderTextTranslations {language,text},alertUrlTranslations {language,text}} fragment F1 on Route {alerts {trip {pattern {code,id},id},id,...F0},id} fragment F2 on Stop {_stoptimesWithoutPatterns2LPFJd:stoptimesWithoutPatterns(startTime:$startTime_1,timeRange:$timeRange_2,numberOfDepartures:$numberOfDepartures_3,omitCanceled:false) {realtimeState,trip {pattern {code,id},route {gtfsId,shortName,longName,mode,color,id,...F1},id}},id}\",\"variables\":{\"id_0\":\"gtt:$stopToReplace\",\"startTime_1\":$startTimeToReplace,\"timeRange_2\":900,\"numberOfDepartures_3\":900}}";
    private final String nextPassagesQuery = "{\"id\":\"q02\",\"query\":\"query StopPageContentContainer_StopRelayQL($id_0:ID!,$startTime_1:Long!,$timeRange_2:Int!,$numberOfDepartures_3:Int!) {node(id:$id_0) {...F2}} fragment F0 on Route {alerts {alertSeverityLevel,effectiveEndDate,effectiveStartDate,trip {pattern {code,id},id},id},id} fragment F1 on Stoptime {realtimeState,realtimeDeparture,scheduledDeparture,realtimeArrival,scheduledArrival,realtime,serviceDay,pickupType,stopHeadsign,stop {id,code,platformCode},trip {gtfsId,directionId,tripHeadsign,wheelchairAccessible,stops {id},pattern {route {gtfsId,shortName,longName,mode,color,agency {name,id},id,...F0},code,stops {gtfsId,code,id},id},id}} fragment F2 on Stop {_stoptimesWithoutPatterns1svkTi:stoptimesWithoutPatterns(startTime:$startTime_1,timeRange:$timeRange_2,numberOfDepartures:$numberOfDepartures_3,omitCanceled:false) {...F1},id}\",\"variables\":{\"id_0\":\"$idToReplace\",\"startTime_1\":\"$startTimeToReplace\",\"timeRange_2\":$timeRangeToReplace,\"numberOfDepartures_3\":900}}\n";
    public List<BusPassage> retrieveBusPassage ( String stopNumber, int nextHours ) throws MatoServiceException {

        String codedId = retrieveCodedStopId(stopNumber,nextHours);
        List<BusPassage> busPassagesList = new ArrayList<>();

        if ( codedId != null ) {

            try {

                String query = replaceIdAndTimeAndNextHoursForPassages(codedId, nextHours);
                HttpResponse<JsonNode> response = Unirest.post(matoEndpoint)
                        .header("content-type", "application/json")
                        .body(query)
                        .asJson();

                com.fasterxml.jackson.databind.JsonNode rootNode = objectMapper.readTree(response.getBody().toString());
                com.fasterxml.jackson.databind.JsonNode dataArray = rootNode.path("data").path("node");
                String nodeName = "";
                while (dataArray.fieldNames().hasNext()) {
                    String next = dataArray.fieldNames().next();
                    if (next.contains("stoptimesWithoutPatterns")) {
                        nodeName = next;
                        break;
                    }
                }
                com.fasterxml.jackson.databind.JsonNode listNode = dataArray.get(nodeName);
                for (int i = 0; i < listNode.size(); i++) {
                    BusPassage busPassage = objectMapper.readValue(listNode.get(i).toString(), BusPassage.class);
                    busPassagesList.add(busPassage);
                }

            } catch ( Exception e ) {
                throw new MatoServiceException(MATO_SERVICE_ERROR);
            }
        }
        return busPassagesList;
    }
    protected String retrieveCodedStopId ( String stopNumber ) throws MatoServiceException {
        return retrieveCodedStopId(stopNumber,12);
    }
    protected String retrieveCodedStopId ( String stopNumber, int nextHours ) throws MatoServiceException {

        String query = replaceStopAndTimeAndNextHoursForId(stopNumber, nextHours);

        HttpResponse<JsonNode> response = Unirest.post(matoEndpoint)
                .header("content-type", "application/json")
                .body(query)
                .asJson();

        try {
            StopRoutes stopRoutes = objectMapper.readValue(response.getBody().toString(), StopRoutes.class);
            return stopRoutes.getData().getStop().getId();
        } catch ( Exception e ) {
            throw new MatoServiceException(MATO_SERVICE_ERROR);
        }
    }
    protected String replaceStopAndTimeAndNextHoursForId(String stopNumber, int nextHours ) {
        return codedIdQuery
                .replace("$stopToReplace",stopNumber)
                .replace("$startTimeToReplace",String.valueOf(Instant.now().getEpochSecond()));
    }
    protected String replaceIdAndTimeAndNextHoursForPassages ( String codedId, int nextHours ) {
        return nextPassagesQuery
                .replace("$idToReplace",codedId)
                .replace("$startTimeToReplace", String.valueOf(Instant.now().getEpochSecond()))
                .replace("$timeRangeToReplace", String.valueOf(60 * 60 * nextHours));
    }




}
