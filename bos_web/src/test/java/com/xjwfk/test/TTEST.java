package com.xjwfk.test;

import org.junit.Test;

import net.sf.json.JSONObject;

public class TTEST {
	@Test
	public void test2() {
		Apple apple = new Apple();
		System.out.println(apple);
		JSONObject jsonObject = JSONObject.fromObject(apple);
		String json = jsonObject.toString();
		System.out.println(json);
	}
}
