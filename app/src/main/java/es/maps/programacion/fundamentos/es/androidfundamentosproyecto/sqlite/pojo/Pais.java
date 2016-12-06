package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo;

/**
 * Created by jvg63 on 05/12/2016.
 */
public class Pais {
    String idPais;
    String idPais2;
    String paisEN;
    String paisES;
    String divisas;
    String himno;
    String icono;

    public Pais(String idPais, String idPais2, String paisEN, String paisES, String divisas, String himno, String icono) {
        this.idPais = idPais;
        this.idPais2 = idPais2;
        this.paisEN = paisEN;
        this.paisES = paisES;
        this.divisas = divisas;
        this.himno = himno;
        this.icono = icono;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getIdPais2() {
        return idPais2;
    }

    public void setIdPais2(String idPais2) {
        this.idPais2 = idPais2;
    }

    public String getPaisEN() {
        return paisEN;
    }

    public void setPaisEN(String paisEN) {
        this.paisEN = paisEN;
    }

    public String getPaisES() {
        return paisES;
    }

    public void setPaisES(String paisES) {
        this.paisES = paisES;
    }

    public String getDivisas() {
        return divisas;
    }

    public void setDivisas(String divisas) {
        this.divisas = divisas;
    }

    public String getHimno() {
        return himno;
    }

    public void setHimno(String himno) {
        this.himno = himno;
    }
}
