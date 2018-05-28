package com.example.yerman.yerry_aguirre_gestion_contactos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yerman.yerry_aguirre_gestion_contactos.view.ContactosFragment;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.FormContactoActivity;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.HistorialFragment;
import com.example.yerman.yerry_aguirre_gestion_contactos.view.TelefonoFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;
    private ContactosFragment contactosFragment;
    private FragmentTransaction transaction;

    private BottomNavigationView mBottonNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottonNavigation=(BottomNavigationView)findViewById(R.id.navigation_bottom);
        mBottonNavigation.setOnNavigationItemSelectedListener(this);
        mTextMessage = (TextView) findViewById(R.id.message);

        if(findViewById(R.id.container_fragment)!=null){
            contactosFragment = new ContactosFragment();
            //En caso de que la actividad se haya iniciado por un Intent con parametros
            // estos los pasamos al fragment como argumentos
//   --> ventaFragment.setArguments(getIntent().getExtras());

            // Añadirmos el fragment al fragment_container(FrameLayout)
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container_fragment,contactosFragment).commit();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle arg=new Bundle();
        transaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_telefono:
                TelefonoFragment telefonoFragment=new TelefonoFragment();
                transaction.replace(R.id.container_fragment, telefonoFragment);
                transaction.addToBackStack(null).commit();
                break;
            case R.id.nav_add_contacto:
                startActivity(new Intent(this,FormContactoActivity.class));
                break;
            case R.id.nav_historial:
                HistorialFragment historialFragment=new HistorialFragment();
                transaction.replace(R.id.container_fragment,historialFragment);
                transaction.addToBackStack(null).commit();
                break;
            case R.id.nav_contactos:
                contactosFragment=new ContactosFragment();
                transaction.replace(R.id.container_fragment, contactosFragment);
                transaction.addToBackStack(null).commit();
                break;
        }
        return true;
    }
}
