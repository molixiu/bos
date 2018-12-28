package com.xjwfk.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xjwfk.domain.Customer;
import com.xjwfk.service.CustomerService;



public class Demo {
	
	@Test
	public void test2() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerService proxy =  (CustomerService) context.getBean("crmClient");
		List<Customer> list1 = proxy.findByDecidedzoneId("1");
		System.out.println(list1);
	}
}
