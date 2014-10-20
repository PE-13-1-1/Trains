package ua.kture.pi13.demo.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import ua.kture.pi1311.entity.Train;

public class Demo {

	public static void main(String[] args) {
		Document doc;
		try {
			List<Train> trainList = new ArrayList<Train>();
			List<String> set = new ArrayList<String>();
			doc = Jsoup.connect("http://www.pz.gov.ua/prrasp/s_khar1.php")
					.get();
			Elements links = doc.getElementsByClass("bs1t");
			for (Element link : links) {
				Elements elems = link.getElementsByTag("td");
				Elements elems1 = link.getElementsByTag("tr");
				for (Element element : elems1) {
					String[] array = element.toString().split("\n");
					for (String str : array) {
						if (str.contains("onclick")) {
							set.add(str);
						}
					}
				}
			}
			for (String element : set) {
				
				element.subSequence(
						element.indexOf("rasptrain"),
						element.indexOf("train")+20);
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
