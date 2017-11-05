package com.goldendays.config;

import java.net.URL;

public class Deneme {
	public static void main(String[] args) {
	        URL u = Thread.currentThread().getContextClassLoader().getResource("");
	        System.out.println(u.getPath());
	}

}
