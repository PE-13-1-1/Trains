package ua.kture.pi1311.entity;

public class Train {
	
	private int trainId;
	
	private String startPoint;
	
	private String finalPoint;
	
	public Train() {
		
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
	
	
}
