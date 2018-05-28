package com.example.yerman.yerry_aguirre_gestion_contactos.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yerman.yerry_aguirre_gestion_contactos.MainActivity;
import com.example.yerman.yerry_aguirre_gestion_contactos.R;
import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.ContactoInterface;
import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.PuenteInterface;
import com.example.yerman.yerry_aguirre_gestion_contactos.model.ConecSqlite;
import com.example.yerman.yerry_aguirre_gestion_contactos.presentator.ContactoPresentator;

import java.util.HashMap;

public class FormContactoActivity extends AppCompatActivity implements ContactoInterface.View {
    private EditText et_nombre,et_numero,et_grupo;
    private FloatingActionButton fab;
    private PuenteInterface puente;
    private ContactoInterface.Presentator presentator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contacto);

        puente=new ContactosFragment();
        presentator=new ContactoPresentator(this);

        et_nombre=(EditText)findViewById(R.id.et_nombre);
        et_numero=(EditText)findViewById(R.id.et_numero);
        et_grupo=(EditText)findViewById(R.id.et_grupo);

        fab = (FloatingActionButton) findViewById(R.id.fab_form_contacto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _send();
            }
        });
    }
    public void _send(){
        HashMap<String,Object> hashContacto=new HashMap<String,Object>();
        hashContacto.put(ConecSqlite.Contacto._ID,et_numero.getText().toString());
        hashContacto.put(ConecSqlite.Contacto.NOMBRE,et_nombre.getText().toString());
        hashContacto.put(ConecSqlite.Contacto.GRUPO,et_grupo.getText().toString());

        Log.w("miMap","el numero es: "+et_numero.getText().toString());
        Log.w("miMap","el numero hashMap es: "+hashContacto.get(ConecSqlite.Contacto._ID).toString());
        presentator.insert_update(hashContacto);
    }

    @Override
    public void notificar(long codigo) {
        if(codigo>0){
            Toast.makeText(this,"Se realizo Correctamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
        }
        this.startActivity(new Intent(this,MainActivity.class));
    }
}
