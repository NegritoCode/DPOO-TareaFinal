package utils;

import java.util.Random;

public class Id {
    private static long counter = 1;

    public static String generateId(String prefix) {
        String randomLetters = generateRandomLetters(2);
        return prefix + "_" + (counter++) + randomLetters;
    }

    private static String generateRandomLetters(int length) {
        Random random = new Random();
        StringBuilder letters = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // esto genera una letra aleatoria entre A y Z
            char letter = (char) ('A' + random.nextInt(26));
            letters.append(letter);
        }
        return letters.toString();
    }
    
    public static String getMonth(int monthIndex) {
    	// TODO: terminar meses
    	String[] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"};
    	
    	return MONTHS[monthIndex];
    }
}
