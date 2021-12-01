package com.swing.foodserving.utility;

import java.util.Random;

public class OTPGenerator {
	
	public static String getOTP(int length) {
		int[] arr1 = {0,1,2,3,4,5,6,7,8,9};
		
		Random r = new Random();
		StringBuilder otp = new StringBuilder();
		
		for (int i = 0; i < length; i++) {
			otp.append(arr1[r.nextInt(9)]);
		}
		
		return otp.toString();
	}
	
	
}
