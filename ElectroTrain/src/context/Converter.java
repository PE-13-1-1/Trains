package ua.kture.pi1311.context;

import java.util.ArrayList;

public class Converter 
{
	public static String[][] convertToArray(ArrayList<ArrayList<String>> list)
	{
		int heigth = list.size();
		int width = list.get(0).size();
		String[][] result = new String[heigth][width];
		for (int i = 0; i < heigth; i++)
			for (int j = 0; j < width; j++)
				result[i][j] = list.get(i).get(j);
		return result;
	}
}
