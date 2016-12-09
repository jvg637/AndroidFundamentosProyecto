package es.maps.programacion.fundamentos.androidfundamentosproyecto.lib.paises.pojo;

/**
 * Created by jvg63 on 05/12/2016.
 */
public class Divisa {
    public String getIdDivisa() {
        return idDivisa;
    }

    public void setIdDivisa(String idDivisa) {
        this.idDivisa = idDivisa;
    }

    public String getIdDivisa2() {
        return idDivisa2;
    }

    public void setIdDivisa2(String idDivisa2) {
        this.idDivisa2 = idDivisa2;
    }

    public String getDivisaES() {
        return divisaES;
    }

    public void setDivisaES(String divisaES) {
        this.divisaES = divisaES;
    }

    public String getDivisaEN() {
        return divisaEN;
    }

    public void setDivisaEN(String divisaEN) {
        this.divisaEN = divisaEN;
    }

    private String idDivisa, idDivisa2, divisaES, divisaEN;

    public Divisa(String idDivisa, String idDivisa2, String divisaES, String divisaEN) {
        this.idDivisa = idDivisa;
        this.idDivisa2 = idDivisa2;
        this.divisaES = divisaES;
        this.divisaEN = divisaEN;
    }
}
