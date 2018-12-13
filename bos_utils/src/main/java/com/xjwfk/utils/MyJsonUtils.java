package com.xjwfk.utils;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class MyJsonUtils {
	
	public static String OjectToJson(Object obj,String[] excStr) {	//对象转json字符串
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excStr); // 把不需要的数据剔除
		String json = JSONObject.fromObject(obj, jsonConfig).toString();
		return json;
	}
	
	public static String OjectToJson(List list,String[] excStr) {	//数组转json字符串
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excStr); // 把不需要的数据剔除
		String json = JSONArray.fromObject(list, jsonConfig).toString();
		return json;
	}
}
