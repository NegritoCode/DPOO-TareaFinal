package utils.constants;

import java.util.Random;

public class Branch {
    public static final String[] BRANCHES = {
            "turismo",
            "custodio",
            "directivo",
            "ingeniero",
            "recursos humanos",
            "contador",
            "analista",
            "desarrollador",
            "médico",
            "profesor"
    };

    public static String getRandomBranch() {
        Random random = new Random();
        return BRANCHES[random.nextInt(BRANCHES.length)];
    }
}