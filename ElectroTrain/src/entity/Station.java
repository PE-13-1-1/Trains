package ua.kture.pi1311.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Station implements Serializable{
	
	private int stationId;
	
	private String stationName;
	
	private ArrayList<Stop> stops;
		
	private String stationURL;

	public Station() {
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || this.getClass() != o.getClass()) 
		{
			return false;
		}
		Station station = (Station) o;
		boolean result = (this.stationId == station.getStationId());
		return result;
	}
	
	
	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationURL() {
		return stationURL;
	}

	public void setStationURL(String stationURL) {
		this.stationURL = stationURL;
	}

	public ArrayList<Stop> getStops() {
		return stops;
	}

	public void setStops(ArrayList<Stop> stops) {
		this.stops = stops;
	}

	
}
