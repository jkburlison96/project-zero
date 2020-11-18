package com.car_dealership.util;

import java.util.Random;

public class Util {
	private static final String lock = "qetuoadgjl";
	private static final String key = "wryisfhk";
	
	public static String encrypt(String s) {
		char[] cArr = new char[s.length()*2];
		Random rnd = new Random();
//		char c = (char) ('A' + rnd.nextInt(26));
		
		for(int i = 0, j = 0; i < s.length(); i++, j++) {
			cArr[i + j] = (char)('A' + rnd.nextInt(26));
			cArr[i + j + 1] = s.charAt(i);
		}
		
		s = String.valueOf(cArr);
		s = lock.concat(s).concat(key);

		return s;
	}
	
	public static String decrypt(String s) {
		s = s.substring(lock.length(), (s.length() - key.length()));
		char[] cArr = new char[s.length()/2];
		int j = 0;
		
		for(int i = 0; i < s.length() ; i++) {
			if(i % 2 == 0) {
				cArr[j++] = s.charAt(i + 1);
			}
		}
		s = String.valueOf(cArr);

		return s;
	}
	
}
