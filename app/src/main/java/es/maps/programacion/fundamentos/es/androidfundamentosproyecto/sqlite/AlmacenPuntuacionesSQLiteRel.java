package es.maps.programacion.fundamentos.es.androidfundamentosproyecto.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

import asteroides.example.org.asteroides.puntuaciones.AlmacenPuntuaciones;
import asteroides.example.org.asteroides.puntuaciones.Puntuacion;

/**
 * Created by jvg63 on 15/11/2016.
 */

public class AlmacenPuntuacionesSQLiteRel extends SQLiteOpenHelper implements AlmacenPuntuaciones {
    public AlmacenPuntuacionesSQLiteRel(Context context) {
        super(context, "puntuaciones", null, 2);
    }

    //Métodos de SQLiteOpenHelper

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios (" + "usu_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nombre TEXT, correo TEXT)");
        db.execSQL("CREATE TABLE puntuaciones2 (" + "pun_id INTEGER PRIMARY KEY AUTOINCREMENT, " + "puntos INTEGER, fecha LONG, usuario INTEGER, " + "FOREIGN KEY (usuario) REFERENCES usuarios (usu_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
            onCreate(db);  //Crea las nuevas tablas
            Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha " + "FROM puntuaciones", null);
            // Recorre la tabla antigua
            while (cursor.moveToNext()) {
                guardarPuntuacion(db, cursor.getInt(0), cursor.getString(1), cursor.getLong(2));
            }                                       //Crea los nuevos registros
            cursor.close();
            db.execSQL("DROP TABLE puntuaciones");  //Elimina tabla antigua
        }
    }

    //Métodos de AlmacenPuntuaciones
    public Vector<Puntuacion> listaPuntuaciones(int cantidad) {
        Vector<Puntuacion> result = new Vector<Puntuacion>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT puntos, nombre, fecha FROM " + "puntuaciones2, usuarios WHERE usuario = usu_id ORDER BY " + "puntos DESC LIMIT " + cantidad, null);
        while (cursor.moveToNext()) {
            result.add(new Puntuacion(cursor.getInt(0), cursor.getString(1), cursor.getLong(2)));
        }
        cursor.close();
        db.close();
        return result;
    }

    public void guardarPuntuacion(int puntos, String nombre, long fecha) {
        SQLiteDatabase db = getWritableDatabase();
        guardarPuntuacion(db, puntos, nombre, fecha);
        db.close();
    }

    public void guardarPuntuacion(SQLiteDatabase db, int puntos, String nombre, long fecha) {
        int usuario = buscaInserta(db, nombre);
        db.execSQL("PRAGMA foreign_keys = ON");
        db.execSQL("INSERT INTO puntuaciones2 VALUES ( null, " + puntos + ", " + fecha + ", " + usuario + ")");
    }

    private int buscaInserta(SQLiteDatabase db, String nombre) {
        Cursor cursor = db.rawQuery("SELECT usu_id FROM usuarios " + "WHERE nombre='" + nombre + "'", null);
        if (cursor.moveToNext()) {
            int result = cursor.getInt(0);
            cursor.close();
            return result;
        } else {
            cursor.close();
            db.execSQL("INSERT INTO usuarios VALUES (null, '" + nombre + "', 'correo@dominio.es')");
            return buscaInserta(db, nombre);
        }
    }
}


