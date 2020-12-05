package com.cursoapp.skyblueapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {
    private Button buttonT;
    private Button buttonT2;
    public static Fragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        buttonT = (Button) view.findViewById(R.id.buttonTeste);
        buttonT.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Fragment infoPacFragment = new PagamentoFragment();
                openFragment(infoPacFragment);
            }
        });

        buttonT2 = (Button) view.findViewById(R.id.buttonTeste2);
        buttonT2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getActivity(), CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
