package utils.constants;

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

    public static int getSpecialtiesCount() {
        return specialties.length;
    }
}