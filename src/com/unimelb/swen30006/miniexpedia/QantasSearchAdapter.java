package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Qantas.QantasFlight;
import com.unimelb.swen30006.Qantas.QantasSearch;

public class QantasSearchAdapter implements ISearchAdapter {
	private QantasSearch qantasSearch;
	
	public QantasSearchAdapter() {
		qantasSearch = new QantasSearch();
	}

	@Override
	public ArrayList<Flight> search(ArrayList<Flight> flights, int numPassengers) {
		ArrayList<QantasFlight> qantasFlights = new ArrayList<>();
		for (Flight f : flights) {
			qantasFlights.add(new QantasFlight(f.departure, f.destination, f.departureTime));
		}
		ArrayList<QantasFlight> qantasResults = qantasSearch.search(qantasFlights, numPassengers);
		
		ArrayList<Flight> results = new ArrayList<>();
		for (QantasFlight q : qantasResults) {
			results.add(new Flight(q.from(), q.to(), q.departAt(), q.arriveAt(), q.fare(), "Qantas", q));
		}
		return results;
	}
}
