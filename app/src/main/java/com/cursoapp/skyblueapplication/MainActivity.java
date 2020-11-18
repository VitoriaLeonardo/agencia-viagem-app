package com.cursoapp.skyblueapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tlbMain);

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
        Fragment homeFragment;
        homeFragment = HomeFragment.newInstance();
        openFragment(homeFragment);


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
}
