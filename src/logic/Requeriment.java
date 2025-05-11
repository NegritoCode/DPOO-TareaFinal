package logic;

public class Requeriment {
    private String type;
    private String data;

    public Requeriment(String type, String data) {
        this.type = type;
        this.data = data;
    }
    
    // Getters y setters
    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}