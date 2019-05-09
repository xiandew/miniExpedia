package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Jetstar.JetstarFlight;
import com.unimelb.swen30006.Jetstar.JetstarSearch;

public class JetstarSearchAdapter implements ISearchAdapter {
	private JetstarSearch jetstarSearch;
	public JetstarSearchAdapter() {
		jetstarSearch = new JetstarSearch();
	}

	@Override
	public ArrayList<Flight> search(ArrayList<Flight> flights, int numPassengers) {
		ArrayList<Flight> results = new ArrayList<>();
		try {
			jetstarSearch.setNumPassengers(numPassengers);
			jetstarSearch.startInputFlights();
			
			for (Flight f : flights) {
				jetstarSearch.inputFlight(f.departure, f.destination, f.departureTime);
			}
			
			jetstarSearch.endInputFlights();
			jetstarSearch.search();
			
			ArrayList<JetstarFlight> jetstarResults = jetstarSearch.getSearchResult(0);
			for (JetstarFlight j : jetstarResults) {
				results.add(new Flight(j.from(), j.to(), j.departAt(), j.arriveAt(), j.fare(), "Jetstar", j));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
