package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by jvg63 on 05/12/2016.
 */

public class Cruza {
    private static final String ENTRADA1 = "C:\\Users\\jvg63\\AndroidStudioProjects\\entrada1.txt";
    private static final String ENTRADA2 = "C:\\Users\\jvg63\\AndroidStudioProjects\\entrada2.txt";
    private static final String SALIDA = "C:\\Users\\jvg63\\AndroidStudioProjects\\salida.txt";

    public static void main(String[] argv) throws IOException {

        BufferedReader entrada1 = new BufferedReader(new FileReader(ENTRADA1));

        ArrayList<String> f1 = new ArrayList<>();

        String aux;
        while ((aux = entrada1.readLine()) != null) {
            f1.add(aux +"~");
        }

        entrada1.close();

        entrada1 = new BufferedReader(new FileReader(ENTRADA2));

        while ((aux = entrada1.readLine()) != null) {
            String temp= new String();


            String divisas = new String();
            for (int i = 0; i < f1.size(); i++) {
                temp = f1.get(i).split("~")[3];

                if (aux.contains(temp)) {

                            f1.set(i,f1.get(i)+ aux.split("~")[0] + "-");

                }

            }

        }

        entrada1.close();

        String temp;

        PrintWriter pw = new PrintWriter(new FileWriter(new File(SALIDA)));


        for (int i = 0; i < f1.size(); i++) {
            temp = f1.get(i);

            pw.write(temp + "\n");

        }

        pw.close();

    }
}
