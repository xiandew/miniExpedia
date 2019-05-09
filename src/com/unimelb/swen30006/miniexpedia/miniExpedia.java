/**
 * This class is created for Workshop's exercises in the subject SWEN30006 Software Design and Modelling at The University of Melbourne .
 * @author 	Patanamon Thongtanunam
 * @version 1.0
 * @since 	2019-04-20
 *
 */

package com.unimelb.swen30006.miniexpedia;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class miniExpedia {

	public static void main(String[] args) throws ParseException {
		
		Scanner in = new Scanner(System.in);
		boolean stopInput = false;
		String[] airlines = {"Jetstar","Qantas","Virgin"};
		

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd&HH:mm");
		System.out.print("Number of Passengers: ");
		int numPassengers = in.nextInt();
		
		ArrayList<Flight> flights = new ArrayList<>();
		
		while(!stopInput) {
			System.out.print("Date & Time (ex. 2019-04-29&10:00): ");
			String dateStr = in.next();

			LocalDateTime date = null;
			//Check date
			try {
				date = LocalDateTime.parse(dateStr,formatter);
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Wrong Date & Time Format.");
				continue;
			}
			
			System.out.print("Departure airport: ");
			String departure = in.next();
			System.out.print("Destination airport: ");
			String destination = in.next();
			
			flights.add(new Flight(departure, destination, date));
			
			System.out.print("More flight? (y/n): ");
			String input = in.next();
			input = input.toLowerCase();
			stopInput = input.equals("n");
			System.out.println(stopInput);
		}
		
		System.out.print("Preferred airline? (0: All, 1: Jetstar, 2: Qantas, 3: Virgin)");
		int airlineIndex = in.nextInt();
		// in.close();
		
		
		
		AdapterFactory adapterFactory = AdapterFactory.getInstance();
		ArrayList<Flight> searchResults = new ArrayList<>();
		if (airlineIndex > 0) {
			airlines = new String[] {airlines[airlineIndex - 1]};
		}
		for (String airline : airlines) {
			ISearchAdapter searchAdapter = adapterFactory.getSearchAdapter(airline);
			searchResults.addAll(searchAdapter.search(flights, numPassengers));
		}
		
		System.out.printf("%n%nSearch results%n%n");
		
		System.out.printf("%5s | %7s | %16s | %10s%n", " ", "airline","Arrival Time", "Fare");
		
		for (int i = 0; i < searchResults.size(); i++) {
			Flight result = searchResults.get(i);
			System.out.printf("%5d | %7s | %16s | %10.2f%n",
					i, result.airline, result.arrivalTime.format(formatter), result.fare);
		}
		
		// Book
		System.out.printf("%n%nPlease enter all %d passengers' details before booking%n", numPassengers);
		
		ArrayList<Passenger> passengers = new ArrayList<>();
		for (int i = 0; i < numPassengers; i++) {
			System.out.printf("Passenger No.%d%n", i);
			System.out.print("Date of birth (ex. 2019-04-29&10:00): ");
			String dobStr = in.next();
			LocalDateTime dob = null;
			//Check date
			try {
				dob = LocalDateTime.parse(dobStr,formatter);
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Wrong Date & Time Format.");
				i--;
				continue;
			}
			System.out.print("First name: ");
			String firstName = in.next();
			System.out.print("Last name: ");
			String lastName = in.next();
			passengers.add(new Passenger(firstName, lastName, dob));
		}
		in.nextLine();
		
		System.out.printf("Select flights to book (eg. 0, 1, 5)%n");
		List<String> flights_no = Arrays.asList(in.nextLine().split(","));
		
		HashMap<String, ArrayList<Flight>> bookingFlights = new HashMap<>();
		
		// categorize booking flights by airlines
		for (String airline : airlines) {
			bookingFlights.put(airline, new ArrayList<>());
		}
		for (String no : flights_no) {
			no = no.replaceAll("\\s+","");
			Flight bookingFlight = searchResults.get(Integer.parseInt(no));
			ArrayList<Flight> airlineBookingFlights = bookingFlights.get(bookingFlight.airline);
			airlineBookingFlights.add(bookingFlight);
			bookingFlights.put(bookingFlight.airline, airlineBookingFlights);
		}
		
		System.out.printf("%n%nConfirmation codes%n%n");
		System.out.printf("%7s | %s%n", "airline", "confirmation code");
		for (String airline : airlines) {
			if (bookingFlights.get(airline).size() > 0) {
				IBookAdapter bookAdapter = adapterFactory.getBookAdapter(airline);
				String confirmCode = bookAdapter.book(bookingFlights.get(airline), passengers);
				System.out.printf("%7s | %s%n", airline, confirmCode);
			}
		}
		
		in.close();
	}
}
