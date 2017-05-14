package com.example.marcelo.androidlab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity
    // Implementamos la interaccion con cada fragment
        implements NavigationView.OnNavigationItemSelectedListener , PerrosFragment.OnFragmentInteractionListener,
GatosFragment.OnFragmentInteractionListener,ConejoFragment.OnFragmentInteractionListener, MonoFragment.OnFragmentInteractionListener,
ContactoFragment.OnFragmentInteractionListener, UbicacionFragment.OnFragmentInteractionListener{

    //Creamos una variable de tipo fragment y otra de tipo Booleano
    Fragment fragment = null;
    Boolean fragmentSeleccionado = false;
    EditText editText;

    //el código en general fue auto completado al crear el proyecto, para interactuar con los fragments
    //no iremos hasta la parte de abajo, al metodo  onNavigationItemSelected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Enlaza nuestro NavigationDrawer con el XMK
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Este método nos indica la accion al pulsar en el boton "Atras"
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Este metodo crea el menu (Los tres puntitos de la esquina superior derecha)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Este metodo permite interactuar con los items del menu creado arriba
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Este metodo nos permite interactuar con los fragments
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Este metodo compara el ID del item seleccionado con todos los IDs.
        int id = item.getItemId();
        //Una vez que el encuentra el item seleccionado cambiamos de fragment
        if (id == R.id.perros) {
            //Creamos el tipo de fragment
            fragment = new PerrosFragment();
            //Le decimos a la aplicacion que ya seleccionamos un item
            fragmentSeleccionado = true;
        } else if (id == R.id.gatos) {
            fragment = new GatosFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.conejo) {
            fragment = new ConejoFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.mono) {
            fragment = new MonoFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.contacto) {
            fragment = new ContactoFragment();
            fragmentSeleccionado = true;
        } else if (id == R.id.ubicacion) {
            fragment = new UbicacionFragment();
            fragmentSeleccionado = true;
        }

        //Preguntamos... Ha sido seleccionado un item?
        if(fragmentSeleccionado){
            //Por verdad llamamos al administrador de fragments y remplazamos el contenido
            // del contenedor por el fragment seleecionado.
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, fragment).commit();

            //Enviamos el titulo del item a nuestro ToolBar
            getSupportActionBar().setTitle(item.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Este metodo nos permite compartir alguna cadena de texto con cualquier aplicacion que necesitemos
    void enviarMensaje(View view){
        editText = (EditText) findViewById(R.id.edit_text);
        String mensaje = editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,mensaje);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(mensaje));
        startActivity(intent);
    }

    //Este metodo se genero al implementar la interaccion con los fragments
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
