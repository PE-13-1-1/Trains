package ua.kture.pi1311.entity;

import java.io.Serializable;

import ua.kture.pi13.demo.enums.DirectionType;

public class Direction implements Serializable{

	private int directionId;
	
	private String directionTitle;
	
	private DirectionType directionType;
	
	public Direction() {
	}

	public int getDirectionId() {
		return directionId;
	}

	public void setDirectionId(int directionId) {
		this.directionId = directionId;
	}

	public String getDirectionTitle() {
		return directionTitle;
	}

	public void setDirectionTitle(String directionTitle) {
		this.directionTitle = directionTitle;
	}
	
	public DirectionType getDirectionType() {
		return this.directionType;
	}
	
	public void setDirectionType(DirectionType directionType) {
		this.directionType = directionType;
	}
		
}
