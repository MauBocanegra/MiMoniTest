package maubocanegra.mimonitest.MainContainer;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import maubocanegra.mimonitest.MainContainer.Busqueda.SearchActivity;
import maubocanegra.mimonitest.MainContainer.Datos.FragmentDatos;
import maubocanegra.mimonitest.R;

public class MainContainer extends AppCompatActivity implements
        FragmentDatos.OnFragmentDatosListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_container);

        // Primero seteamos el toolbar del MainContainer
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Hacemos el tweak del actionBar para que sirva como Home
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Obtenemos el drawer layout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        // -------- NavigationSelectedListener -------- //
        // rutina encargada de los clicks en las secciones del NavBar
        navView = (NavigationView) findViewById(R.id.navigation_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.drawer_educacion) {
                    Intent intent = new Intent(MainContainer.this, SearchActivity.class);
                    //Activamos la transicion si es post-lollipop
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) MainContainer.this,navView, "");
                        Bundle bundle = options.toBundle();
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }

                Snackbar.make(navView, menuItem.getTitle() + " pressed", Snackbar.LENGTH_LONG).show();
                //menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });

        Fragment fragmentDatos = FragmentDatos.newInstance();
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frameLayout, fragmentDatos);
        tx.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //En caso de que sea el boton HOME
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String[] arr) {}
}
