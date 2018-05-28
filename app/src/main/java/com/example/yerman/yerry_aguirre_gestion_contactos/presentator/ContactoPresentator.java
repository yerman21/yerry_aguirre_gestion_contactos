package com.example.yerman.yerry_aguirre_gestion_contactos.presentator;

import android.support.annotation.NonNull;

import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.ContactoInterface;
import com.example.yerman.yerry_aguirre_gestion_contactos.model.ContactoModel;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.ContactosFragment;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.FormContactoActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactoPresentator implements ContactoInterface.Presentator {
    private ContactoInterface.Model model;
    private ContactoInterface.View view;
    private FormContactoActivity view2;

    public ContactoPresentator(ContactosFragment view){
        this.view=view;
        model=new ContactoModel(view.getContext(),this);
    }
    public ContactoPresentator(FormContactoActivity view){
        this.view2=view;
        model=new ContactoModel(view,this);
    }

    @Override
    public void notificar(long codigo) {
        view.notificar(codigo);
    }

    @Override
    public ArrayList<HashMap<String, Object>> lista() {
        return model.lista();
    }

    @Override
    public void insert_update(HashMap<String, Object> contacto) {
        if(!contacto.isEmpty())
            model.insert_update(contacto);
    }
}
