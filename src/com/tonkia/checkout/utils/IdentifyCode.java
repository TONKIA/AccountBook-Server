package com.tonkia.checkout.utils;

import java.util.Random;

public class IdentifyCode {
	private static Random random = new Random();

	public static String generateIndentifyCode(int count) {
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < count; i++) {
			int m = random.nextInt(10);
			code.append(m + "");
		}
		return code.toString();
	}
}
