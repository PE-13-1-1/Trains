package ua.kture.pi1311.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;
import ua.kture.pi1311.entity.Train;

public class MSsqlTrainDAO implements TrainDAO {
	private static final String SQL__FIND_TRAIN_BY_ID = "SELECT * FROM Train WHERE trainId=?;";
	private static final String SQL__UPDATE_TRAIN = "UPDATE Train SET [startPoint]=?, [finalPoint]=?, [status]=?,"
			+ "[trainNumber]=?,[trainURL]=?, [scheduleId]=? WHERE [trainId]=?;";
	private static final String SQL__INSERT_TRAIN = "INSERT INTO Train (startPoint, finalPoint, status, "
			+ "trainNumber,trainURL,scheduleId) VALUES (?, ?, ?, ?, ?,?);";
	private static final String SQL__DELETE_TRAIN = "DELETE FROM Train WHERE trainId=?";

	public boolean insertTrain(Train train) {
		Connection con = null;
		boolean result = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			result = insertTrain(con, train);
			con.commit();
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	public boolean insertTrain(Connection con, Train train) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = true;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_TRAIN,
					Statement.RETURN_GENERATED_KEYS);
			mapTrainForInsert(train, pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			result = false;
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	public Train findTrain(int trainId) {
		Connection con = null;
		Train train = null;
		try {
			con = MSsqlDAOFactory.getConnection();
			train = findTrain(con, trainId);
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return train;
	}
	private Train findTrain(Connection con, int trainId) throws SQLException {
		PreparedStatement pstmt = null;
		Train train = null;
		try {
			pstmt = con.prepareStatement(SQL__FIND_TRAIN_BY_ID);
			pstmt.setLong(1, trainId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				train = unMapTrain(rs);
			}
			return train;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	public boolean updateTrain(Train train) {
		Connection con = null;
		boolean updateResult = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			updateResult = updateTrain(con, train);
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return updateResult;
	}
	private boolean updateTrain(Connection con, Train train) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_TRAIN);
			mapTrainForInsert(train, pstmt);
			int updatedRows = pstmt.executeUpdate();
			con.commit();
			result = updatedRows != 0;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	public boolean deleteTrain(int trainId) {
		Connection con = null;
		Boolean result = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			deleteTrain(trainId, con);
			result = true;
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	private void deleteTrain(long id, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_TRAIN);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw e;
				}
			}
		}

	}
	private void mapTrainForInsert (Train train, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, train.getStartPoint());
		pstmt.setString(2, train.getFinalPoint());
		pstmt.setString(3, train.getStatus());
		pstmt.setInt(4, train.getTrainNumber());
		pstmt.setString(5, train.getTrainUrl());
		pstmt.setInt(6, train.getScheduleId());
	}
	private Train unMapTrain(ResultSet rs) throws SQLException {
		Train train = new Train();
		train.setStartPoint(rs.getString(MapperParameters.TRAIN_START_POINT));
		train.setFinalPoint(rs.getString(MapperParameters.TRAIN_FINAL_POINT));
		train.setTrainId(rs.getInt(MapperParameters.TRAIN_ID));
		train.setStatus(rs.getString(MapperParameters.TRAIN_STATUS));
		train.setTrainNumber(rs.getInt(MapperParameters.TRAIN_NUMBER));
		train.setScheduleId(rs.getInt(MapperParameters.SCHEDULE_ID));
		train.setTrainUrl(rs.getString(MapperParameters.TRAIN_URL));
		return train;
	}
}
