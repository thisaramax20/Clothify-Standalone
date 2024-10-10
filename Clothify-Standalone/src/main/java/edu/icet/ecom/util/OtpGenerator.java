package edu.icet.ecom.util;

import java.util.Random;

public class OtpGenerator {
    public static String generateOtp(int length){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
