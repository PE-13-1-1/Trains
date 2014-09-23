package ua.kture.pi1311.entity;

import java.util.List;

public class Train {
	
	private int trainId;
	
	private String startPoint;
	
	private String finalPoint;
	
	private List<Station> stations;
	
	private String direction;
	
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


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
}
