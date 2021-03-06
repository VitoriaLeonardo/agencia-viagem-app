package com.cursoapp.skyblueapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PagRealizadoFragment extends Fragment {
    public Button continuarBuscando;

    public PagRealizadoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pag_realizado, container, false);

        continuarBuscando = (Button) view.findViewById(R.id.btn_continuar_buscando);
        continuarBuscando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment homeFragment = new HomeFragment();
                openFragment(homeFragment);
            }
        });

        // return inflater.inflate(R.layout.fragment_pagamento, container, false);

        return view;
    }


    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){

    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
