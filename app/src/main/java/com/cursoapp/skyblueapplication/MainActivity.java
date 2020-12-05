package com.cursoapp.skyblueapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //private Toolbar toolbar;

    //Relacionado ao spinner
    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Relacionado ao spinner
        /*spinner = (Spinner) findViewById(R.id.spn_parcelas);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcelas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        */

/*

        Toolbar toolbar = (Toolbar) findViewById(R.id.tlbMain);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Navegacao.historico.size()>0)
                    Navegacao.historico.remove(Navegacao.historico.size());
            }
        });

        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Home");
        Fragment homeFragment;
        homeFragment = HomeFragment.newInstance();
        Navegacao.historico.add(homeFragment);
        openFragment(homeFragment);*/

        //Inicializando com o homeFragment
        /*Fragment homeFragment;
        homeFragment = HomeFragment.newInstance();
        openFragment(homeFragment);*/


        //BottomNavigation

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        //getSupportActionBar().setTitle("Home");
                        Fragment homeFragment = new HomeFragment().newInstance();
                        Navegacao.historico.add(homeFragment);
                        openFragment(homeFragment);
                        break;
                    case R.id.navigation_perfil:
                        //getSupportActionBar().setTitle("Perfil");
                        Fragment perfilFragment = PerfilFragment.newInstance();
                        Navegacao.historico.add(perfilFragment);
                        openFragment(perfilFragment);
                        break;
                }
                return true;
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void openFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    //Relacionado ao spinner
    /*public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }*/

    //SearchView
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
        }
    }
    //Search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Toolbar toolbar = (Toolbar) findViewById(R.id.tlbMain);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }
}
