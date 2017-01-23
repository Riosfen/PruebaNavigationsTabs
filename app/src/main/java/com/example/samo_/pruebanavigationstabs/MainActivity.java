package com.example.samo_.pruebanavigationstabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.samo_.pruebanavigationstabs.fragments.FragmentContadores;
import com.example.samo_.pruebanavigationstabs.fragments.FragmentCorredor;
import com.example.samo_.pruebanavigationstabs.fragments.FragmentEcuacion;
import com.example.samo_.pruebanavigationstabs.fragments.FragmentSolitario;
// implementar todas las clases con OnFragmentInteractionListener
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentEcuacion.OnFragmentInteractionListener,
        FragmentContadores.OnFragmentInteractionListener, FragmentCorredor.OnFragmentInteractionListener,
        FragmentSolitario.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // eliminamos esto
        /**
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
         */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // definir las opciones del menu
        Intent intent = null;

        switch(id){
            case R.id.llamada:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:634469391"));
                break;
            case R.id.navegador:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://222.google.es"));
                break;
            case R.id.gps:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?q=calle olivo los palacios y villafranca"));
                break;
        }
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // para cargar los fagment
        Fragment f = null;

        switch(id){
            case R.id.contadores:
                f = new FragmentContadores();
                getSupportFragmentManager().beginTransaction().add(R.id.content_main, f).commit();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_main, f).commit();
                break;
            case R.id.segundoGrado:
                f = new FragmentEcuacion();
                getSupportFragmentManager().beginTransaction().add(R.id.content_main, f).commit();
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                fragmentTransaction4.replace(R.id.content_main, f).commit();
                break;
            case R.id.imageButon:
                f = new FragmentCorredor();
                getSupportFragmentManager().beginTransaction().add(R.id.content_main, f).commit();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                fragmentTransaction3.replace(R.id.content_main, f).commit();
                break;
            case R.id.solitario:
                f = new FragmentSolitario();
                getSupportFragmentManager().beginTransaction().add(R.id.content_main, f).commit();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.replace(R.id.content_main, f).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
