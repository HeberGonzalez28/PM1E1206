package Models;

public class Pais {
    private int id;
    private String pais;

    public Pais(int id, String pais) {
        this.id = id;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
