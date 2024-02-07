package com.ots.samples;

public class ThreadLocalDemo {

	public static void main(String[] args) {
		
		ThreadLocal<String> tl1=new ThreadLocal<String>();
		
		tl1.set("Selenium");
		
		System.out.println(tl1.get());
		
	}

}
