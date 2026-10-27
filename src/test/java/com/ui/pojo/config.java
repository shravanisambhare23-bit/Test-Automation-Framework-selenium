package com.ui.pojo;

import java.util.Map;

public class config {
// in config.json file environment has 2 values so its working as map 
Map<String, Environment> environments;

public Map<String, Environment> getEnvironments() {
	// TODO Auto-generated method stub
	return environments;
}
public void setEnvironments(Map<String, Environment> environments) {
	this.environments = environments;
}



}
