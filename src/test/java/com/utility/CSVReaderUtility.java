package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.ui.pojo.User;

public class CSVReaderUtility {
	public static Iterator<User> readCsvFile(String fileName) {
		File csvFile = new File(System.getProperty("user.dir") + "\\testData\\"+fileName);
		FileReader fileReader = null;
		String[] line;
		ArrayList<User> userList = null;
		User userData;
		
		try {
			fileReader = new FileReader(csvFile);
			CSVReader csvReader = new CSVReader(fileReader);
			line = csvReader.readNext();
			userList = new ArrayList<User>();
			// declaration should happen outside try block here keeping it to understand
			
			while ((line = csvReader.readNext()) != null) {
				userData = new User(line[0], line[1]);
				userList.add(userData);
			}
//			// iterate through the ArrayList 
//			 for (User user : userList) {
//				System.out.println(user);
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvValidationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();
	}
}
// this all code will read CSV file and store the data from csv to ArrayList then 
// we can use this arraylist and pass data to our dataProvider