package ua.kture.pi13.demo.parser;

import java.io.IOException;
import java.util.ArrayList;
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
import ua.kture.pi1311.dao.TrainDAO;
import ua.kture.pi1311.entity.Train;

public class Demo {

	private static List<Integer> getTrainNumbers(Elements links) {
		List<Integer> list = new ArrayList<Integer>();
		for (Element link : links) {
			Elements elems = link.getElementsByTag("td");
			int count =0;
			for (int i=0;i<elems.size();i++) {
				if (count==0)
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

	private static Set<String> getURLS(Elements links) {
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
			mainSet.add((String) element.subSequence(
					element.indexOf("rasptrain"), element.indexOf("train") + 20));
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

	private static void insertTrain(Train train) {
		TrainDAO trainDAO = DAOFactory.getDAOFactory(DAOFactory.MSSQL)
				.getTrainDAO();
		trainDAO.insertTrain(train);
	}

	private static void parsePage() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.pz.gov.ua/prrasp/s_khar1.php")
					.get();
			Elements links = doc.getElementsByClass("bs1t");
			Set<String> set = getURLS(links);
			Map<Integer,String> map = getImages(links);
			List<String> list = getPoints(links);
			Set<Integer> ints = map.keySet();
			List<Integer> intList = getTrainNumbers(links);
			for (int i = 0; i<set.size();i++) {
				Train train = new Train();
				train.setScheduleId(i+1);
				train.setStartPoint(list.get(i).split("-")[0]);
				train.setFinalPoint(list.get(i).split("-")[1]);
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
				}
				else {
					train.setStatus("Каждый день");
				}
				train.setTrainNumber(intList.get(i));
				insertTrain(train);
			}
			System.out.println("Code 0");
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		parsePage();
	}

}
