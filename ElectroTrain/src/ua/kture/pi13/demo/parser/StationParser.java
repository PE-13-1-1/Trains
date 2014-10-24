package ua.kture.pi13.demo.parser;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.TrainDAO;

public class StationParser {

	private static void LetsWork() {
		TrainDAO trainDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getTrainDAO();
		trainDAO.getTrainURL();
	}
	public static void main(String[] args) {
		LetsWork();
	}

}
