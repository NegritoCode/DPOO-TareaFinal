package logic;

public class GlobalAgency {
    private static Agency instance;

    private GlobalAgency() {}

    public static Agency getInstance() {
        if (instance == null) {
            instance = new Agency("Los papus");

            // TODO: inicializar datos aqui
        }
        return instance;
    }
}