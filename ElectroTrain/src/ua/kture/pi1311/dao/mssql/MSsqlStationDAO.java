package ua.kture.pi1311.dao.mssql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.DirectionDAO;
import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.dao.mssql.MSsqlDAOFactory;
import ua.kture.pi1311.electrotrain.parametres.MapperParameters;

public class MSsqlStationDAO implements StationDAO{

	private static final String SQL__FIND_STATION_BY_ID = "SELECT * FROM Station WHERE stationId=?;";
	private static final String SQL__UPDATE_STATION = "UPDATE Station SET [stationName]=?, [directionId]=? WHERE [stationId]=?;";
	private static final String SQL__INSERT_STATION = "INSERT INTO Station (stationName, directionId) VALUES (?, ?);";
	private static final String SQL__DELETE_STATION = "DELETE FROM Station WHERE stationId=?";
	DirectionDAO directionDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
			.getDirectionDAO(); 
	public boolean insertStation(Station station) {
		Connection con = null;
		boolean result = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			result = insertStation(con, station);
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

	public boolean insertStation(Connection con, Station station) throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = true;
		try {
			pstmt = con.prepareStatement(SQL__INSERT_STATION,
					Statement.RETURN_GENERATED_KEYS);
			mapStationForInsert(station, pstmt);
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
	public Station getStationById(long stationId) {
		Connection con = null;
		Station station = null;
		try {
			con = MSsqlDAOFactory.getConnection();
			station = getStationById(con, stationId);
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return station;
	}

	private Station getStationById(Connection con, long stationId) throws SQLException {
		PreparedStatement pstmt = null;
		Station station = null;
		try {
			pstmt = con.prepareStatement(SQL__FIND_STATION_BY_ID);
			pstmt.setLong(1, stationId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				station = unMapStation(rs);
			}
			return station;
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
	public Station findStation(int stationId) {
		Connection con = null;
		Station station = null;
		try {
			con = MSsqlDAOFactory.getConnection();
			station = findStation(con, stationId);
		} catch (SQLException e) {
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
		return station;
	}

	private Station findStation(Connection con, int stationId) throws SQLException {
		PreparedStatement pstmt = null;
		Station station = null;
		try {
			pstmt = con.prepareStatement(SQL__FIND_STATION_BY_ID);
			pstmt.setLong(1, stationId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				station = unMapStation(rs);
			}
			return station;
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
	
	private void mapStationForInsert(Station station, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, station.getStationName());
		pstmt.setInt(2, station.getDirection().getDirectionId());
	}
	private Station unMapStation(ResultSet rs) throws SQLException {
		Station station = new Station();
		station.setStationId(rs.getInt(MapperParameters.STATION_ID));
		station.setDirection(directionDAO.findDirection(rs.getInt(MapperParameters.STATION_DIRECTION_ID)));
		station.setStationName(rs.getString(MapperParameters.STATION_NAME));
		return station;
	}
	


	public boolean updateStation(Station station) {
		Connection con = null;
		boolean updateResult = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			updateResult = updateStation(con, station);
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

	private boolean updateStation(Connection con, Station station) throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__UPDATE_STATION);
			mapStationForInsert(station, pstmt);
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
	public boolean deleteStation(int stationId) {
		Connection con = null;
		Boolean result = false;
		try {
			con = MSsqlDAOFactory.getConnection();
			deleteStation(stationId, con);
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

	private void deleteStation(long id, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL__DELETE_STATION);
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

}
