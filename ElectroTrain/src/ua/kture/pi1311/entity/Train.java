package ua.kture.pi1311.entity;

import java.util.List;

public class Train {
	
	private int trainId;
	
	private String startPoint;
	
	private String finalPoint;
	
	private List<Station> stations;
	
	private Direction direction;
	
	private int status;
	
	private int trainNumber;
	
	private int urlId;
	
	public Train() {
		
	}
	

	@Override
	public boolean equals(Object o) {
		if (o == null || this.getClass() != o.getClass()) {
			return false;
		}
		Train train = (Train) o;
		boolean result = (this.trainId == train.getTrainId());
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

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getTrainNumber() {
		return trainNumber;
	}


	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}


	public int getUrlId() {
		return urlId;
	}


	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}


}
