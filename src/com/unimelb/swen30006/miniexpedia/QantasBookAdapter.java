package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

import com.unimelb.swen30006.Qantas.QantasBook;
import com.unimelb.swen30006.Qantas.QantasFlight;
import com.unimelb.swen30006.Qantas.QantasPassenger;

public class QantasBookAdapter implements IBookAdapter {
	private QantasBook qantasBook;
	public QantasBookAdapter() {
		qantasBook = new QantasBook();
	}

	@Override
	public String book(ArrayList<Flight> flights, ArrayList<Passenger> passengers) {
		ArrayList<QantasFlight> qF = new ArrayList<>();
		ArrayList<QantasPassenger> qP = new ArrayList<>();
		
		for (Flight f : flights) {
			qF.add((QantasFlight) f.flight);
		}
		for (Passenger p : passengers) {
			qP.add(new QantasPassenger(p.firstName, p.lastName, p.dob));
		}
		
		return qantasBook.book(qF, qP);
	}

}
