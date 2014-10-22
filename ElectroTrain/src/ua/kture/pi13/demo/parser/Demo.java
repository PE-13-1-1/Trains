package ua.kture.pi13.demo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import ua.kture.pi1311.dao.DAOFactory;
import ua.kture.pi1311.dao.StationDAO;
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.entity.Station;
import ua.kture.pi1311.entity.Train;

public class Demo {

	private static List<String> stations = new ArrayList<String>();
	private static List<String> stationsURL = new ArrayList<String>();
	private static Map<String,String> stationToURL = new HashMap<String,String>();
	
	private static List<Integer> getTrainNumbers(Elements links) {
		List<Integer> list = new ArrayList<Integer>();
		for (Element link : links) {
			Elements elems = link.getElementsByTag("td");
			int count = 0;
			for (int i = 0; i < elems.size(); i++) {
				if (count == 0)
					list.add(Integer.parseInt(elems.get(0).text()));
				count++;
			}
		}
		return list;
	}

	private static List<String> getPoints(Elements links) {
		List<String> list = new ArrayList<String>();
		for (Element link : links) {
			Elements elems = link.getElementsByTag("td");
			for (Element element : elems) {
				if (element.text().contains("-")) {
					list.add(element.text());
				}
			}
		}
		return list;
	}

	private static Set<String> getURLS(Elements links, int whichObject) {
		List<String> set = new ArrayList<String>();
		Set<String> mainSet = new HashSet<String>();
		for (Element link : links) {
			Elements elems = link.getElementsByTag("tr");
			for (Element element : elems) {
				String[] array = element.toString().split("\n");
				for (String str : array) {
					if (str.contains("onclick")) {
						set.add(str);
					}
				}
			}
		}
		for (String element : set) {
			if (whichObject == 1) {
				mainSet.add((String) element.subSequence(
						element.indexOf("rasptrain"),
						element.indexOf("train") + 20));
			} else if (whichObject == 2) {
				mainSet.add((String) element.subSequence(
						element.indexOf("rrep"), element.indexOf("onmouseover")));
			}
		}
		return mainSet;
	}

	private static Map<Integer, String> getImages(Elements links) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		int key = 0;
		for (Element link : links) {
			Elements elems = link.getElementsByTag("tr");
			for (Element element : elems) {
				String[] array = element.toString().split("\n");
				for (String elem : array) {
					if (elem.contains("img")) {
						String sequence = (String) elem.subSequence(
								elem.indexOf("title=\"") + 7,
								elem.indexOf("\" border"));
						map.put(key, sequence);
					}
				}
				key++;
			}
		}
		return map;
	}

	private static void insertTrain(TrainDAO trainDAO, Train train) {
		trainDAO.insertTrain(train);
	}

	private static void parseTrainPage() {
		TrainDAO trainDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getTrainDAO();
		trainDAO.truncateTrain();
		Document doc;
		try {
			doc = Jsoup.connect("http://www.pz.gov.ua/prrasp/s_khar1.php")
					.get();
			Elements links = doc.getElementsByClass("bs1t");
			Set<String> set = getURLS(links, 1);
			Map<Integer, String> map = getImages(links);
			List<String> list = getPoints(links);
			Set<Integer> ints = map.keySet();
			List<Integer> intList = getTrainNumbers(links);
			for (int i = 0; i < set.size(); i++) {
				Train train = new Train();
				train.setScheduleId(i + 1);
				train.setTrainId(i + 2);
				if (list.get(i).split("-").length > 2) {
					train.setStartPoint(list.get(i).split("-")[0]);
					train.setFinalPoint(list.get(i).split("-")[2]);
				} else {
					train.setStartPoint(list.get(i).split("-")[0]);
					train.setFinalPoint(list.get(i).split("-")[1]);
				}
				train.setTrainUrl((String) set.toArray()[i]);
				if (ints.contains(i)) {
					Iterator<Integer> iterator = ints.iterator();
					while (iterator.hasNext()) {
						Integer iterValue = iterator.next();
						if (iterValue == i) {
							String status = map.get(iterValue);
							train.setStatus(status);
						}
					}
				} else {
					train.setStatus("Каждый день");
				}
				train.setTrainNumber(intList.get(i));
				insertTrain(trainDAO, train);
			}
			System.out.println("Code 0");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}


	private static void LetsWorkWithStations() {
		TrainDAO trainDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getTrainDAO();
		Set<String> set = trainDAO.getTrainURL();
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String url = "http://www.pz.gov.ua/prrasp/" + iterator.next();
			parseStationPage(url);
		}
		makeStationToDB();
	}

//	private static void parseStationPage(String url) {
//		StationDAO stationDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
//				.getStationDAO();
//		Document doc;
//		try {
//			doc = Jsoup.connect(url).get();
//			Elements links = doc.getElementsByClass("bs1t");
//			Set<String> set = getURLS(links, 2);
//			Set<String> newSet1 = new HashSet<String>();
//			Iterator<String> iterator = set.iterator();
//			while (iterator.hasNext()) {
//				String string = iterator.next();
//				newSet1.add((String) string.subSequence(string.indexOf("('")+2, string.indexOf("')\"")));
//			}
//			Set<String> stations1 = getStationNames(links);
//			stations.addAll(stations1);
//			stationsURL.addAll(newSet1);
//			if (stations.size() !=stationsURL.size() ) {
//				System.out.println(url);
//				while (stations.size() != stationsURL.size()) {
//					stations.add("Blah");
//				}
//			}
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
	private static void parseStationPage(String url) {
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements links = doc.getElementsByClass("bs1t");
			Set<String> set = getURLS(links, 2);
			Set<String> newSet1 = new HashSet<String>();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String string = iterator.next();
				newSet1.add((String) string.subSequence(string.indexOf("('")+2, string.indexOf("')\"")));
			}
			Set<String> stations1 = getStationNames(links);
			if (stations1.size() == newSet1.size() ) {
				stations.addAll(stations1);
				stationsURL.addAll(newSet1);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	private static void makeStationToDB() {
		StationDAO stationDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getStationDAO();
		stationDAO.truncateStation();
		for (int i = 0;i < stations.size();i++) {
			stationToURL.put(stations.get(i), stationsURL.get(i));
		}
		Set<String> keySet = stationToURL.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();
			String adressURL = stationToURL.get(element);
			Station station = new Station();
			station.setStationName(element);
			station.setStationURL(adressURL);
			stationDAO.insertStation(station);
		}
		
	}
	private static Set<String> getStationNames(Elements links) {
		Set<String> list = new HashSet<String>();
		for (Element link : links) {
			int count = 0;
			Elements elems = link.getElementsByTag("td");
			for (Element element : elems) {
				if(count ==0)
					list.add(element.text());
				count++;
			}
		}
		return list;
	}
	public static void main(String[] args) {
		// parsePage();
		LetsWorkWithStations();
	}
}
