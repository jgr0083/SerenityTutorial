package com.studentapp.utils;

import java.util.Random;

import gherkin.StringUtils.ToString;

public class TestUtils {
	
	public static String getRandomValue() {
		Random random = new Random();
		int randomInt = random.nextInt(100000);
		
		return Integer.toString(randomInt);
		
	}
}
