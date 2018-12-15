package com.xjwfk.test;

public class Apple {
	private String name ="apple";
	private double privice = 1212.1;
	@Override
	public String toString() {
		return "Apple [name=" + name + ", privice=" + privice + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrivice() {
		return privice;
	}
	public void setPrivice(double privice) {
		this.privice = privice;
	}
}
