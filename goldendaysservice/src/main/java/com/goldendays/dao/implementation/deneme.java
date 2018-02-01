package com.goldendays.dao.implementation;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class deneme {
 
	public static void main(String[] args) {
		Locale locale= new Locale("TR", "TURKEY");
		Calendar.getInstance(TimeZone.getTimeZone("GMT+03:00")).getTime().getTime();
		String[] toppings= {"a","b","c","d","e","f"};
		for (String str : toppings) {
			System.out.println(str + Calendar.getInstance(TimeZone.getTimeZone("GMT+03:00"),locale).getTime().getTime());
		}
		
	}
}
