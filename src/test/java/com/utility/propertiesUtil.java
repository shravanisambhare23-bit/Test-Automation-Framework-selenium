package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class propertiesUtil {
// to read property file
	
	public static String readProperty(Env env , String propertyName)  {
		// FileNotFoundException -> compile time exception 
		File propFile =  new File(System.getProperty("user.dir")+"\\Config\\"+env+".properties");
		FileReader fileReader = null;
		Properties properties = new Properties();
		try {
			fileReader = new FileReader(propFile);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// will handle only files having extension .properties if this is not present it will throw exception
		String value = properties.getProperty(propertyName.toUpperCase());
		return value;
	}
}
