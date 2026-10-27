package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.ui.pojo.User;

public class ExcelReaderUtility {

	public static Iterator<User> readExcelFile(String FileName) {

		File excelData = new File(System.getProperty("user.dir") + "\\testData\\"+FileName);
		XSSFWorkbook wb = null;
		XSSFSheet sheet;
		List<User> userList = null;
		Row row;
		Cell emailAddresscell;
		Cell passwordCell;
		User user;
		Iterator<Row> rowIterator;
		try {
			wb = new XSSFWorkbook(excelData);
			sheet = wb.getSheet("LoginDataSheet");
			userList = new ArrayList();	
			rowIterator = sheet.iterator();
			rowIterator.next();
	while(rowIterator.hasNext()) {
		row = rowIterator.next();
		emailAddresscell = row.getCell(0);
		passwordCell = row.getCell(1);
		user = new User(emailAddresscell.toString(), passwordCell.toString());
		userList.add(user);	
		wb.close();
	}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList.iterator();		
	}
}
