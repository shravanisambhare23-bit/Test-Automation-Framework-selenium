package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojo.Environment;
import com.ui.pojo.config;

public class JSONUtility {
	public static Environment readJson(Env env) {
		// TODO Auto-generated method stub

		Gson gson = new Gson();
		File jsonfile = new File(System.getProperty("user.dir") + "\\Config\\config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonfile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config config = gson.fromJson(fileReader, config.class); // here we will use reader
		// fromjson is an overloaded method-to load data from multiple sources
		// using this entire data from json will mapped with config.java
		Environment environment = config.getEnvironments().get("QA");
		return environment;

	}

}
