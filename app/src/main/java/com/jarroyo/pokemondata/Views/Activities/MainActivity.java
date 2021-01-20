package com.jarroyo.pokemondata.Views.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.pedant.SweetAlert.SweetAlertDialog;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jarroyo.pokemondata.Interfaces.iComunicaFragments;
import com.jarroyo.pokemondata.R;
import com.jarroyo.pokemondata.Utils.General;
import com.jarroyo.pokemondata.Utils.Mensaje;
import com.jarroyo.pokemondata.Views.Fragments.ListPokeFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        iComunicaFragments {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    SweetAlertDialog sweetAlertDialog;
    Mensaje mensaje;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        General.context = this;
        sweetAlertDialog = new SweetAlertDialog(this);
        mensaje = new Mensaje();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_fragment,new ListPokeFragment());
        fragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //para cerrar automaticamente el menu
        drawerLayout.closeDrawer(GravityCompat.START);


        switch(menuItem.getItemId()){
            case R.id.listPokemon:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container_fragment,new ListPokeFragment());
                fragmentTransaction.commit();

                break;
            case R.id.listBerries:

                break;
            case R.id.salir:
                sweetAlertDialog = mensaje.MensajeConfirmacionAdvertenciaConBotones(this,"Mensaje",
                        "Desea cerrar el aplicativo?");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        finish();
                    }
                });
                sweetAlertDialog.show();
                break;
        }
        return false;
    }

    @Override
    public void  infoMensaje(String dato,int tipoMensaje){

        switch(tipoMensaje){
            case 1:
                sweetAlertDialog.dismiss();
                mensaje.MensajeAdvertencia(this,"Advertencia",dato);
                break;
            case 2:
                sweetAlertDialog = mensaje.progreso(this,dato);
                sweetAlertDialog.show();
                break;
            case 3:
                sweetAlertDialog.dismiss();
                break;
        }

    }
}