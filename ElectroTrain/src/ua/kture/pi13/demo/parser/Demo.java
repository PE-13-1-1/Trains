package ua.kture.pi13.demo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import ua.kture.pi1311.entity.Train;

public class Demo {

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
			mainSet.add((String) element.subSequence(element.indexOf("rasptrain"),
					element.indexOf("train") + 20));
		}
		return mainSet;
	}
	
	private static Map<Integer,String> getImages(Elements links) {
		Map<Integer,String> map = new HashMap<Integer, String>();
		int key = 0;
		for (Element link : links) {
			Elements elems = link.getElementsByTag("tr");
			for (Element element : elems) {
				String[] array = element.toString().split("\n");
				for (String elem :array) {
					if (elem.contains("img")) {
						String sequence = (String) elem.subSequence(elem.indexOf("title=\"") + 7,
								elem.indexOf("\" border"));
						map.put(key, sequence);
					}
				}
				key++;
			}
		}
		return map;
	}

	private static void parsePage() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.pz.gov.ua/prrasp/s_khar1.php")
					.get();
			Elements links = doc.getElementsByClass("bs1t");
			//getURLS(links);
			//getImages(links);
			getPoints(links);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void main(String[] args) {
		parsePage();
	}

}
