package com.example.yerman.yerry_aguirre_gestion_contactos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConecSqlite extends SQLiteOpenHelper{
    private static final String DB_NAME  = "db_contactos";
    private static final int DB_VERSION = 1;
    private final String CREATE_CONTACTOS="CREATE TABLE CONTACTO(ID_NUMERO INTEGER NOT NULL PRIMARY KEY UNIQUE,NOMBRE TEXT NOT NULL,GRUPO TEXT NOT NULL)";

    public ConecSqlite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CONTACTOS");
        onCreate(db);
    }

    public static class Contacto{
        public static final String TABLE="CONTACTO";
        public static final String _ID="ID_NUMERO";
        public static final String NOMBRE="NOMBRE";
        public static final String GRUPO="GRUPO";
        public static final String[] COLUMNAS=new String[]{_ID,NOMBRE,GRUPO};
    }
}