package com.rabiloo.sharedocument.util;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encode {

	public static String encode(String password) {
		BCryptPasswordEncoder bcryt = new BCryptPasswordEncoder();
		return bcryt.encode(password);
	}

	public static Boolean checkPassword(String password, String encodePassword) {
		BCryptPasswordEncoder bcryt = new BCryptPasswordEncoder();
		return bcryt.matches(password, encodePassword);
	}

	public static Integer randomPassword() {
		int min = 100000;
		int max = 999999;
		try {
			Random random = new Random();
			int range = max - min + 1;
			int randomNum = min + random.nextInt(range);
			return randomNum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static void main(String[] args) {
		System.out.println(checkPassword("123456", "$2a$10$kH8Vr09KwADbNmAL56XWlOURUv71FBsiwGxQ3NrCf5CAe39ynPMoC"));
	}
}
