package com.example.yerman.yerry_aguirre_gestion_contactos.adapter;

import com.example.yerman.yerry_aguirre_gestion_contactos.R;
import com.example.yerman.yerry_aguirre_gestion_contactos.model.ConecSqlite;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.ContactosFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ContactoViewHolder> {
    private ArrayList<HashMap<String,Object>> lista;
    public ContactosAdapter(ArrayList<HashMap<String,Object>> lista){
        this.lista=lista;
    }
    //Éste método es llamado cuando el ViewHolder necesita ser inicializado.
    // Especificamos el layout que cada elemento de RecyclerView debería usar.
    // Ésto se hace al inflar el layout usando LayoutInflater, pasando el resultado al constructor del ViewHolder.
    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactos_recycler, parent, false);
        ContactoViewHolder pvh = new ContactoViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //para especificar el contenido de cada elemento del RecyclerView.
    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).get(ConecSqlite.Contacto.NOMBRE).toString());
        holder.grupo.setText(lista.get(position).get(ConecSqlite.Contacto.GRUPO).toString());
        holder.nCelular.setText(lista.get(position).get(ConecSqlite.Contacto._ID).toString());
        holder.img.setImageResource(R.drawable.ic_person);

        //holder.nombre.setText(listaProductos (position).get("nombre").toString());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        RelativeLayout card_info,card_btn_option;
        TextView nombre;
        TextView grupo;
        TextView nCelular;
        ImageView img;

        ContactoViewHolder(View itemView) {
            super(itemView);
            context=itemView.getContext();
            card_info = (RelativeLayout) itemView.findViewById(R.id.card_info);
            card_btn_option = (RelativeLayout) itemView.findViewById(R.id.card_btn_option);
            nombre = (TextView)itemView.findViewById(R.id.card_nombre);
            grupo = (TextView)itemView.findViewById(R.id.card_grupo);
            nCelular = (TextView)itemView.findViewById(R.id.card_numero);
            img = (ImageView)itemView.findViewById(R.id.card_foto);
        }

        void setOnClickListener(){
            card_info.setOnClickListener(this);
            card_btn_option.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.card_info:
                    if(ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE)
                            != PackageManager.PERMISSION_GRANTED)
                        return;
                    context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+nCelular.getText().toString())));
                    break;
                case R.id.card_btn_option:
                    Intent intent=new Intent(context, ContactosFragment.class);
                    context.startActivity(intent);
                    break;
            }
        }
    }
}
