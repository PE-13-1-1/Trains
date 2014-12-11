package ua.kture.pi1311.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Train implements Serializable {

	private int trainId;

	private String startPoint;

	private String finalPoint;

	private ArrayList<Stop> stops;

	private String status;

	private int trainNumber;

	private String trainUrl;
	
	static final long serialVersionUID = 4706426902916659405L;
	
	public Train() {

	}

	@Override
	public boolean equals(Object o) 
	{
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Train train = (Train) o;
		boolean result = (this.startPoint.equals(train.startPoint)
				&& this.finalPoint.equals(train.finalPoint)
				&& this.trainNumber == train.trainNumber);
		return result;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(String finalPoint) {
		this.finalPoint = finalPoint;
	}

	public ArrayList<Stop> getStops() {
		return this.stops;
	}

	public void setStops(ArrayList<Stop> stops) {
		this.stops = stops;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainUrl() {
		return trainUrl;
	}

	public void setTrainUrl(String trainUrl) {
		this.trainUrl = trainUrl;
	}


}
