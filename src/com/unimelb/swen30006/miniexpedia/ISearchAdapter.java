package com.unimelb.swen30006.miniexpedia;

import java.util.ArrayList;

public interface ISearchAdapter {
	public ArrayList<Flight> search(ArrayList<Flight> flights, int numPassengers);
}
