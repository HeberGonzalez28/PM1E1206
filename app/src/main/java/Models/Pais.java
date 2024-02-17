package Models;

public class Pais {
    private int id;
    private String codigopais;
    private String pais;

    public Pais(int id, String codigopais, String pais) {
        this.id = id;
        this.codigopais = codigopais;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigopais() {
        return codigopais;
    }

    public void setCodigopais(String codigopais) {
        this.codigopais = codigopais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
