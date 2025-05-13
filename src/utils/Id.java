package utils;

public class Id {
    private static long counter = 1;

    public static String generateId(String prefix) {
        return prefix + "_" + System.currentTimeMillis() + "_" + (counter++);
    }
}
