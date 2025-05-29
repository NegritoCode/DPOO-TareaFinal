package utils.constants;

import java.util.Random;

public class Specialty {
    public static final String[] specialties = {
            "informática",
            "medicina",
            "derecho",
            "administración",
            "turismo",
            "idiomas",
            "recursos humanos",
            "ingeniería civil",
            "ingeniería eléctrica",
            "contabilidad"
    };

    public static String getRandomSpecialty() {
        Random random = new Random();
        return specialties[random.nextInt(specialties.length)];
    }
}