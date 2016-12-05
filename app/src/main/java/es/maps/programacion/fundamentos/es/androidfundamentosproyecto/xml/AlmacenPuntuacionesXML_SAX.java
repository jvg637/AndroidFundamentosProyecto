package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.xml;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * Created by jvg63 on 13/11/2016.
 */

class AlmacenStringesXML_SAX {
    private static String FICHERO = "C:\\Users\\jvg63\\AndroidStudioProjects\\AndroidFundamentosProyecto\\app\\src\\main\\res\\xml\\paisesdivisas.xml";
    private static String FICHERO_OUT = "C:\\Users\\jvg63\\AndroidStudioProjects\\paisesdivisasout.xml";

    private boolean cargadaLista;

    public AlmacenStringesXML_SAX() {

        cargadaLista = false;
    }

    private class Pais {
        String name;
        String countryCode;
        String currency;
        String currencyCode;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    }



        public void leerXML(InputStream entrada) throws Exception {
            SAXParserFactory fabrica = SAXParserFactory.newInstance();
            SAXParser parser = fabrica.newSAXParser();
            XMLReader lector = parser.getXMLReader();

            ManejadorXML manejadorXML = new ManejadorXML();

            lector.setContentHandler(manejadorXML);
            lector.parse(new InputSource(entrada));
            cargadaLista = true;
        }

        private class ManejadorXML extends DefaultHandler {
            private StringBuilder cadena= new StringBuilder();
            private Pais pais;
            private PrintWriter pw = new PrintWriter(new File(FICHERO_OUT));

            private ManejadorXML() throws FileNotFoundException {
            }

            @Override
            public void startElement(String uri, String nombreLocal, String nombreCualif, Attributes atr) throws SAXException {
                cadena.setLength(0);
                if (nombreCualif.equals("Table")) {
                    pais = new Pais();

                    //String.setFecha(Long.parseLong(atr.getValue("fecha")));
                }
            }

            @Override
            public void characters(char ch[], int comienzo, int lon) {
                cadena.append(ch, comienzo, lon);
            }

            @Override
            public void endElement(String uri, String nombreLocal, String nombreCualif) throws SAXException {
                System.out.println(nombreLocal + " "  + cadena.toString());
                System.out.println(nombreCualif + " "  + cadena.toString());
                if (nombreCualif!=null && cadena!=null) {
                if (nombreCualif.equals("Name")) {
                    pais.setName(cadena.toString());
                } else if (nombreCualif.equals("CountryCode")) {
                    pais.setCountryCode(cadena.toString());
                } else if (nombreCualif.equals("Currency")) {
                    pais.setCurrency(cadena.toString());
                } else if (nombreCualif.equals("CurrencyCode")) {
                    pais.setCurrencyCode(cadena.toString());
                } else if (nombreCualif.equals("Table"))

                {
                    String cadena = new String(pais.getName() + ";" + pais.getCountryCode() + ";" + pais.getCurrency() + ";" + pais.getCurrencyCode());
                    //listaStringes.add(pais.getName() + ";" + pais.getCountryCode() + ";" + pais.getCurrency() + ";" + pais.getCountryCode());
                    pw.write(cadena + "\n");
                }}
            }

            @Override
            public void endDocument() throws SAXException {
                pw.close();
            }
        }



    public static void main(String[] args) {

        AlmacenStringesXML_SAX almacen = new AlmacenStringesXML_SAX();
        try {
            almacen.leerXML(new FileInputStream(FICHERO));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
