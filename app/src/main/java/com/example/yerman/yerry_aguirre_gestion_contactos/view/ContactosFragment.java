package com.example.yerman.yerry_aguirre_gestion_contactos.view;

import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.ContactoInterface;
import com.example.yerman.yerry_aguirre_gestion_contactos.R;
import com.example.yerman.yerry_aguirre_gestion_contactos.adapter.ContactosAdapter;
import com.example.yerman.yerry_aguirre_gestion_contactos.interfaces.PuenteInterface;
import com.example.yerman.yerry_aguirre_gestion_contactos.model.ConecSqlite;
import com.example.yerman.yerry_aguirre_gestion_contactos.presentator.ContactoPresentator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactosFragment extends Fragment implements ContactoInterface.View,PuenteInterface{
    private FloatingActionButton fab;
    private ContactoInterface.Presentator presentator;
    private RecyclerView rv;
    private ContactosAdapter adapter;
    private ArrayList<HashMap<String,Object>> lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_contactos, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab_add_contacto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().startActivity(new Intent(getContext(),FormContactoActivity.class));
            }
        });

        presentator=new ContactoPresentator(this);
        lista= new ArrayList<HashMap<String,Object>>();
        rv = (RecyclerView)view.findViewById(R.id.rv_contacto);

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setHasFixedSize(true);
        // LinearLayoutManager hará que tu RecyclerView parezca una ListView.
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new ContactosAdapter(lista);
        rv.setAdapter(adapter);
        return view;

    }

    @Override
    public void notificar(long codigo) {
        if (codigo>0)
            Toast.makeText(getContext(),"Se realizo Correctamente",Toast.LENGTH_LONG).show();
    }

    @Override
    public void insert_update(HashMap<String,Object> contacto){
        presentator.insert_update(contacto);
    }
}
