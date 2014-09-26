package ua.kture.pi13.demo.parser;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;




public class Demo {

	public static void main(String[] args) {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.pz.gov.ua/prrasp/rasptrain.php?train=2212").get();
			Elements links = doc.getElementsByClass("bs1t");
			for (Element link : links) {
			  Elements elems = link.getElementsByTag("td");
			  for (Element element : elems) {
				  System.out.println(element.text());
			  }
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
