package com.humanize.server.helper;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {

	public int randInt(int min, int max) {
	    Random random = new Random();
	    int randomNum = random.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
}
