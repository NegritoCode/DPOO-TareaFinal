package utils;

public class Id {
    private static long counter = 1;

    public static String generateId(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "_" + (counter++);
    }
    
    public static String getMonth(int monthIndex) {
    	// TODO: terminar meses
    	String[] MONTHS = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio"};
    	
    	return MONTHS[monthIndex];
    }
}
