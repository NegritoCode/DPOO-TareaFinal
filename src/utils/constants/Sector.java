package utils.constants;

import java.util.Random;

public class Sector {
    public static final String[] SECTORS = {
            "turismo",
            "salud",
            "educación",
            "construcción",
            "tecnología",
            "finanzas",
            "gobierno",
            "manufactura",
            "transporte",
            "comercio"
    };

    public static String getRandomSector() {
        Random random = new Random();
        return SECTORS[random.nextInt(SECTORS.length)];
    }
}
