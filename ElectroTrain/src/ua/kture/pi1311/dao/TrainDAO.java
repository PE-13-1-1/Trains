package ua.kture.pi1311.dao;

import ua.kture.pi1311.entity.Train;

public interface TrainDAO {
	
	public void insertTrain(Train train);
	
	public Train findTrain (int trainId);
	
	public boolean updateTrain (Train train);
	
	public boolean deleteStation (Train train);
	
}
