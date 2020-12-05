package com.cursoapp.skyblueapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class PagamentoFragment extends Fragment {

    public RadioButton radioCartao;
    public RadioButton radioBoleto;
    public RadioGroup radioGroup;
    public Button btnPagar;

    //Relacionado ao spinner
    /*public Spinner spinner;*/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*FragmentManager frg = getFragmentManager();
        PagamentoFragment pgtFragment = (PagamentoFragment) frg.findFragmentById(R.id.spn_parcelas);

        //Relacionado ao spinner
        spinner = (Spinner) (R.id.spn_parcelas);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parcelas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        /*radioBoleto = (RadioButton) getView().findViewById(R.id.rdoBoleto);
        radioCartao = (RadioButton) getView().findViewById(R.id.rdoCartao);

        final LinearLayout linearBoleto = (LinearLayout) getView().findViewById(R.id.linear_boleto_pag);
        final LinearLayout linearCartao = (LinearLayout) getView().findViewById(R.id.linear_cartao_pag);


        radioCartao.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (linearCartao.getVisibility() == v.VISIBLE) {
                            linearCartao.setVisibility(v.GONE);
                            linearBoleto.setVisibility(v.INVISIBLE);

                        } else {
                            linearCartao.setVisibility(v.VISIBLE);
                        }
                    }
                }
        );

        radioBoleto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (linearBoleto.getVisibility() == v.VISIBLE) {
                            linearBoleto.setVisibility(v.GONE);
                            linearCartao.setVisibility(v.INVISIBLE);
                        } else {
                            linearBoleto.setVisibility(v.VISIBLE);
                        }
                    }
                }
        );*/

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagamento, container, false);


        btnPagar = (Button) view.findViewById(R.id.btn_pagar_pag);
        btnPagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Fragment pagRealizado = new PagRealizadoFragment();
                openFragment(pagRealizado);
            }
        });
        // return inflater.inflate(R.layout.fragment_pagamento, container, false);

        return view;
    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState){

        radioBoleto = (RadioButton) getView().findViewById(R.id.rdoBoleto);
        radioCartao = (RadioButton) getView().findViewById(R.id.rdoCartao);
        radioGroup = (RadioGroup) getView().findViewById(R.id.rdoGroupPag);

        //View view = inflater.inflate(R.layout.fragment_pagamento, container, false);

        final LinearLayout linearBoleto = (LinearLayout) getView().findViewById(R.id.linear_boleto_pag);
        final LinearLayout linearCartao = (LinearLayout) getView().findViewById(R.id.linear_cartao_pag);


        radioCartao.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearBoleto.setVisibility(v.INVISIBLE);
                        if (linearCartao.getVisibility() == v.VISIBLE) {
                            linearCartao.setVisibility(v.VISIBLE);
                        } else {
                            linearCartao.setVisibility(v.VISIBLE);
                        }
                    }
                }
        );

        radioBoleto.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linearCartao.setVisibility(v.INVISIBLE);
                        if (linearBoleto.getVisibility() == v.VISIBLE) {
                            linearBoleto.setVisibility(v.VISIBLE);
                        } else {
                            linearBoleto.setVisibility(v.VISIBLE);
                        }
                    }
                }
        );

        super.onViewCreated(view,savedInstanceState);
    }
}

