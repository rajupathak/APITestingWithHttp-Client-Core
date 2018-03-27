package com.qa.Data;

import java.util.HashMap;
import java.util.Map;

public class Headers {
	String Key;
	String Value;

	static HashMap<String, String> map = new HashMap<String, String>();

	public static HashMap<String, String> getHeader() {

		map.put("Content-Type", "application/json");
		return map;

	}

	// key and value from map

	public String getKey() {
		for (Map.Entry<String, String> mapobj : map.entrySet()) {
			Key = mapobj.getKey();
		}
		return Key;
	}

	public String getValue() {
		for (Map.Entry<String, String> mapobj : map.entrySet()) {
			Value = mapobj.getValue();
		}
		return Value;
	}

}
