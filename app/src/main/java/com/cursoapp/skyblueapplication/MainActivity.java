package com.cursoapp.skyblueapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private Toolbar toolbar;

    //Spinner
    public Spinner spinner;

    TextView emptyView;
    //Search que funcionou
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializando com o homeFragment
        Fragment homeFragment = new HomeFragment().newInstance();
        Navegacao.historico.add(homeFragment);
        openFragment(homeFragment);


        //BottomNavigation
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
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


        //SearchView
            /*listView = (ListView) findViewById(R.id.listView);

            list = new ArrayList<>();
            list.add("Apple");
            list.add("Banana");
            list.add("Pineapple");
            list.add("Orange");
            list.add("Lychee");
            list.add("Gavava");
            list.add("Peech");
            list.add("Melon");
            list.add("Watermelon");
            list.add("Papaya");

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);*/
        }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    //Abrindo fragment
    private void openFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //ActionBar
    public static void mostrarActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().show();
    }
    public static void esconderActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().hide();
    }
}
