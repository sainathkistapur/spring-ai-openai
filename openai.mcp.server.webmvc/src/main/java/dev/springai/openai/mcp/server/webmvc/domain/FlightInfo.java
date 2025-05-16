package dev.springai.openai.mcp.server.webmvc.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightInfo {

     private int id;
     private String airline;

     private String airlineCode;

     private String flightNumber;

    private String departure;

    private String arrival;

    private String departureDate;
    private String departureTime;

    private String arrivalDate;
    private String arrivalTime;

}
