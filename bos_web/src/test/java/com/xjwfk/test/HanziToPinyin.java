package com.xjwfk.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.xjwfk.utils.PinYin4jUtils;

public class HanziToPinyin {
	
	@Test
	public void demo() {
		String province = "广东省";
		String city = "广州市";
		String district = "天河区";
		
		province = province.substring(0, province.length()-1);
		city = city.substring(0,city.length()-1);
		district = district.substring(0,district.length()-1);
		String info = province + city + district;
		
		String[] headByString = PinYin4jUtils.getHeadByString(info);
		String shortCode = StringUtils.join(headByString);
		String toPinyin = PinYin4jUtils.hanziToPinyin(city,"");
		
		System.out.println(province);
		System.out.println(shortCode);
		System.out.println(toPinyin);
	}
}
