package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Vector;

import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.DatosPaisesDivisas;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Divisa;
import es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite.pojo.Pais;


/**
 * Created by jvg63 on 14/11/2016.
 */

public class PaisesDivisasSQLite extends SQLiteOpenHelper {
    private Context context;

    public PaisesDivisasSQLite(Context context) {
        super(context, "paises_divisas", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            DatosPaisesDivisas.datosPaisesDivisas(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de una nueva versión habría que actualizar las tablas
    }

    /*@Override
    public void guardarPuntuacion(int puntos, String nombre, long fecha) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO puntuaciones VALUES ( null, " + puntos + ", '" + nombre + "', " + fecha + ")");
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "No se puede guardar puntuaciones.Debe eliminar la base de datos actual manualmente ya que está en la versión 2", Toast.LENGTH_SHORT).show();
        }
    }*/

    public Vector<Pais> listarPaises() {
        Vector<Pais> result = new Vector<Pais>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            //String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN", "HIMNO", "ICONO"};
            String[] CAMPOS = {"ID_PAIS",  "ID_PAIS_2" , "PAIS_EN" , "PAIS_ES" , "DIVISAS" , "HIMNO" , "ICON", "URL"};
            Cursor cursor = db.query("paises", CAMPOS, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                result.add(new Pais(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7)));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error a listar los paises", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Vector<Divisa> listarDivisas() {
        Vector<Divisa> result = new Vector<Divisa>();
        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN"};

            Cursor cursor = db.query("divisas", CAMPOS, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                result.add(new Divisa(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error al listar los divisas", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    public Pais getPais(String idPais) {

       Pais pais=null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            //String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN", "HIMNO", "ICONO"};
            String[] CAMPOS = {"ID_PAIS",  "ID_PAIS_2" , "PAIS_EN" , "PAIS_ES" , "DIVISAS" , "HIMNO" , "ICON", "URL"};
            Cursor cursor = db.query("paises", CAMPOS, "ID_PAIS=?", new String[]{idPais}, null, null, null, null);

            while (cursor.moveToNext()) {
                pais = new Pais(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error al obtener el pais", Toast.LENGTH_SHORT).show();
        }
        return pais;
    }

    public Divisa getDivisa(String idDivisa) {

        Divisa divisa =null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            /// Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones ORDER BY puntos DESC LIMIT " + cantidad, null);

            // REEMPLAZAR RAWQUERY POR QUERY
            String[] CAMPOS = {"ID_DIVISA", "ID_DIVISA2" , "DIVISA_ES" , "DIVISA_EN"};
            Cursor cursor = db.query("divisas", CAMPOS, "ID_DIVISA=?", new String[]{idDivisa}, null, null, null, null);

            while (cursor.moveToNext()) {
                divisa = new Divisa(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            }
            cursor.close();
            db.close();
        } catch (SQLiteException es) {
            Toast.makeText(context, "Error al obtener divisa", Toast.LENGTH_SHORT).show();
        }
        return divisa;
    }
}
