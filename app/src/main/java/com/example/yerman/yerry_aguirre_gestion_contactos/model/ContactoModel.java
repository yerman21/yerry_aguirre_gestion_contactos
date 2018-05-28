package com.example.yerman.yerry_aguirre_gestion_contactos.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.ContactoInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactoModel implements ContactoInterface.Model {
    private ConecSqlite conn;
    private SQLiteDatabase database;
    private ContactoInterface.Presentator presentator;

    public ContactoModel(Context context, ContactoInterface.Presentator presentator){
        conn=new ConecSqlite(context);
        this.presentator=presentator;
    }
    private SQLiteDatabase getDatabase(){
        if(database==null){
            //Create and/or open a database that will be used for reading and writing.
            // The first time this is called, the database will be opened and onCreate, onUpgrade and/or onOpen will be called.
            //Once opened successfully, the database is cached, so you can call this method every time you need
            // to write to the database. (Make sure to call close when you no longer need the database.)
            // Errors such as bad permissions or a full disk may cause this method to fail,
            // but future attempts may succeed if the problem is fixed.

            //Crea o abre la db que sera usada para escribir o leer
            database=conn.getWritableDatabase();
        }
        return  database;
    }

    private HashMap<String,Object> cursor_to_contacto(Cursor cursor){
        HashMap<String,Object> contacto=new HashMap<String,Object>();
        contacto.put(ConecSqlite.Contacto._ID,cursor.getInt(cursor.getColumnIndex(ConecSqlite.Contacto._ID)));
        contacto.put(ConecSqlite.Contacto.NOMBRE,cursor.getString(cursor.getColumnIndex(ConecSqlite.Contacto.NOMBRE)));
        contacto.put(ConecSqlite.Contacto.GRUPO,cursor.getString(cursor.getColumnIndex(ConecSqlite.Contacto.GRUPO)));
        return contacto;
    }

    public boolean validarNumero(String numero){
        Cursor cursor = getDatabase().query(ConecSqlite.Contacto.TABLE,null,
                "ID_NUMERO = ?", new String[]{numero}, null, null,null);
        if(cursor.moveToNext()){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<HashMap<String, Object>> lista() {
        Cursor cursor=getDatabase().query(ConecSqlite.Contacto.TABLE,ConecSqlite.Contacto.COLUMNAS,null,null,null,null,null);
        ArrayList<HashMap<String, Object>> listaContactos=new ArrayList<HashMap<String, Object>>();
        while(cursor.moveToNext()){
            listaContactos.add(cursor_to_contacto(cursor));
        }
        return listaContactos;
    }

    @Override
    public void insert_update(HashMap<String, Object> contacto) {
        ContentValues values=new ContentValues();
        String numero=contacto.get(ConecSqlite.Contacto._ID).toString();

        values.put(ConecSqlite.Contacto._ID,numero);
        values.put(ConecSqlite.Contacto.NOMBRE,contacto.get(ConecSqlite.Contacto.NOMBRE).toString());
        values.put(ConecSqlite.Contacto.GRUPO, contacto.get(ConecSqlite.Contacto.GRUPO).toString());

        //validar si existe el NUMERO. return TRUE si existe
        if(!validarNumero(numero)){
            presentator.notificar(database.insert(ConecSqlite.Contacto.TABLE,null,values));
        }else{
            presentator.notificar(getDatabase().update(ConecSqlite.Contacto.TABLE,values,"ID_NUMERO=?",new String[]{numero}));
        }
    }
}
