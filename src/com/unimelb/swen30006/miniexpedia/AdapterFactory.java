package com.unimelb.swen30006.miniexpedia;

public class AdapterFactory {
	private static AdapterFactory adapterFactory = new AdapterFactory();
	private ISearchAdapter searchAdapter = null;
	private IBookAdapter bookAdapter = null;
	
	private AdapterFactory() {}
	
	public static AdapterFactory getInstance() {
		return adapterFactory;
	}
	
	public ISearchAdapter getSearchAdapter(String airline) {
		if (airline.equals("Jetstar")) {
			searchAdapter = new JetstarSearchAdapter();
		}
		if (airline.equals("Qantas")) {
			searchAdapter = new QantasSearchAdapter();
		}
		if (airline.equals("Virgin")) {
			searchAdapter = new VirginSearchAdapter();
		}
		return searchAdapter;
	}
	
	public IBookAdapter getBookAdapter(String airline) {
		if (airline.equals("Jetstar")) {
			bookAdapter = new JetstarBookAdapter();
		}
		if (airline.equals("Qantas")) {
			bookAdapter = new QantasBookAdapter();
		}
		if (airline.equals("Virgin")) {
			bookAdapter = new VirginBookAdapter();
		}
		
		return bookAdapter;
	}
}
