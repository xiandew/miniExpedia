package com.unimelb.swen30006.miniexpedia;

import java.time.LocalDateTime;

public class Passenger {
	public String firstName;
	public String lastName;
	public LocalDateTime dob;

	public Passenger(String firstName, String lastName, LocalDateTime dob) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}

}
