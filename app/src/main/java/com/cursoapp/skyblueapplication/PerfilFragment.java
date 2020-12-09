package com.cursoapp.skyblueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cursoapp.skyblueapplication.Classes.MeuIP;

public class PerfilFragment extends Fragment {
    private Button entrarPerfil;


    public static Fragment newInstance() {
        PerfilFragment perfilFragment = new PerfilFragment();
        return perfilFragment;
    }

    public PerfilFragment() {

    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        esconderActionBar(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);


    }

    public static void esconderActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().hide();
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        entrarPerfil = (Button) view.findViewById(R.id.btn_entrar_perfil);
        entrarPerfil.setText(MeuIP.textButtom);
        entrarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);

            }

        });
    }
}


