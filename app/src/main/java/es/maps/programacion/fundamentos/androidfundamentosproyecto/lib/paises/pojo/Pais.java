package es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo;

/**
 * Created by jvg63 on 05/12/2016.
 */
public class Pais {
    private  String idPais;
    private  String idPais2;
    private  String paisEN;
    private  String paisES;
    private  String divisas;
    private  String himno;
    private  String icono;
    private  String urlES;
    private  String urlEN;

    public String getUrlES() {
        return urlES;
    }

    public void setUrlES(String urlES) {
        this.urlES = urlES;
    }

    public Pais(String idPais, String idPais2, String paisEN, String paisES, String divisas, String himno, String icono, String urlES, String urlEN) {
        this.idPais = idPais;
        this.idPais2 = idPais2;
        this.paisEN = paisEN;
        this.paisES = paisES;
        this.divisas = divisas;
        this.himno = himno;
        this.icono = icono;
        this.urlES = urlES;
        this.urlEN = urlEN;
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

    public String getUrlEN() {
        return urlEN;
    }

    public void setUrlEN(String urlEN) {
        this.urlEN = urlEN;
    }
}
