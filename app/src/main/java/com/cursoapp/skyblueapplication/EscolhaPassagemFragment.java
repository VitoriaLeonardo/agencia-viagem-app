package com.cursoapp.skyblueapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EscolhaPassagemFragment extends Fragment {

    public EscolhaPassagemFragment() {
        // Required empty public constructor
    }
    public static EscolhaPassagemFragment newInstance(String param1, String param2) {
        EscolhaPassagemFragment fragment = new EscolhaPassagemFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_escolha_passagem, container, false);
    }
}
