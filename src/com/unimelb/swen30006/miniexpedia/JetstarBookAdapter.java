package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Jetstar.JetstarBook;
import com.unimelb.swen30006.Jetstar.JetstarFlight;
import com.unimelb.swen30006.Jetstar.JetstarPassenger;

public class JetstarBookAdapter implements IBookAdapter {
	private JetstarBook jetstarBook;
	public JetstarBookAdapter() {
		jetstarBook = new JetstarBook();
	}

	@Override
	public String book(ArrayList<Flight> flights, ArrayList<Passenger> passengers) {
		ArrayList<JetstarFlight> jF = new ArrayList<>();
		ArrayList<JetstarPassenger> jP = new ArrayList<>();
		
		for (Flight f : flights) {
			jF.add((JetstarFlight) f.flight);
		}
		for (Passenger p : passengers) {
			jP.add(new JetstarPassenger(p.firstName, p.lastName, p.dob));
		}

		String confirmCode = "Booking Failed";
		try {
			confirmCode = jetstarBook.requestBooking(jF, jP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return confirmCode;
	}

}
