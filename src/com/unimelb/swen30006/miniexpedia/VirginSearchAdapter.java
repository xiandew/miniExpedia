package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Virgin.VirginFlight;
import com.unimelb.swen30006.Virgin.VirginSearch;

public class VirginSearchAdapter implements ISearchAdapter {
	private VirginSearch virginSearch;
	public VirginSearchAdapter() {
		virginSearch = new VirginSearch();
	}

	@Override
	public ArrayList<Flight> search(ArrayList<Flight> flights, int numPassengers) {
		ArrayList<VirginFlight> virginResults = new ArrayList<>();
		for (Flight f : flights) {
			virginResults.addAll(virginSearch.search(f.departure, f.destination, f.departureTime, numPassengers));
		}
		
		ArrayList<Flight> results = new ArrayList<>();
		for (VirginFlight v : virginResults) {
			results.add(new Flight(v.getDeparture(), v.getDestination(), v.getDepartureTime(), v.getArrivalTime(), v.getAirfare(), "Virgin", v));
		}
		
		return results;
	}
	
}
