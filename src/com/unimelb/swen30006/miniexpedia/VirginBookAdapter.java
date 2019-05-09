package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Virgin.VirginBook;
import com.unimelb.swen30006.Virgin.VirginFlight;
import com.unimelb.swen30006.Virgin.VirginPassenger;

public class VirginBookAdapter implements IBookAdapter {
	private VirginBook virginBook;
	public VirginBookAdapter() {
		virginBook = new VirginBook();
	}

	@Override
	public String book(ArrayList<Flight> flights, ArrayList<Passenger> passengers) {
		ArrayList<VirginFlight> vF = new ArrayList<>();
		ArrayList<VirginPassenger> vP = new ArrayList<>();

		for (Flight f : flights) {
			vF.add((VirginFlight) f.flight);
		}
		for (Passenger p : passengers) {
			vP.add(new VirginPassenger(p.firstName, p.lastName, p.dob));
		}
		
		return virginBook.book(vF, vP);
		
	}
}
