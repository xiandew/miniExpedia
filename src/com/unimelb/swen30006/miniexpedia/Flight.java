package com.unimelb.swen30006.miniexpedia;

import java.time.LocalDateTime;

public class Flight {
	public String departure;
	public String destination;
	public LocalDateTime departureTime;
	public LocalDateTime arrivalTime;
	public double fare;
	public String airline;
	public Object flight;

	private void setupQueryingFlight(String departure, String destination, LocalDateTime departureTime) {
		this.departure = departure;
		this.destination = destination;
		this.departureTime = departureTime;
	}
	
	public Flight(String departure, String destination, LocalDateTime departureTime) {
		setupQueryingFlight(departure, destination, departureTime);
	}
	
	public Flight(String departure, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, double fare, String airline, Object flight) {
		setupQueryingFlight(departure, destination, departureTime);
		this.arrivalTime = arrivalTime;
		this.fare = fare;
		this.airline = airline;
		this.flight =  flight;
	}
}
