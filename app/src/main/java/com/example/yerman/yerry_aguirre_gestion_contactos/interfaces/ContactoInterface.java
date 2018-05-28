package com.example.yerman.yerry_aguirre_gestion_contactos.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

public interface ContactoInterface {
    interface View{
        void notificar(long codigo);
    };
    interface Presentator{
        void notificar(long codigo);
        ArrayList<HashMap<String,Object>> lista();
        void insert_update(HashMap<String,Object> contacto);
    };
    interface Model{
        ArrayList<HashMap<String,Object>> lista();
        void insert_update(HashMap<String,Object> contacto);
    };
}
