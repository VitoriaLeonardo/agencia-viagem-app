package com.cursoapp.skyblueapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PagRealizBoletoFragment extends Fragment {
    public Button continuarBuscando;

    public PagRealizBoletoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pag_realiz_boleto, container, false);

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
}
