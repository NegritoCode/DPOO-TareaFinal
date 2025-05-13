package logic;

public class Requeriment {
    private String type;
    private String data;

    public Requeriment(String type, String data) {
        this.type = type;
        this.data = data;
    }
    
    // Getters
    public String getType() {
        return type;
    }

    public String getData() {
        return data;
    }
    
    // Setters
    public void setType(String type) {
        this.type = type;
    }

    public void setData(String data) {
        this.data = data;
    }
}