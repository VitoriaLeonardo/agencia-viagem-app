package com.cursoapp.skyblueapplication;

import android.app.Activity;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PagamentoFragment extends Fragment {

    public RadioButton radioCartao;
    public RadioButton radioBoleto;
    public RadioGroup radioGroup;
    public Button btnPagar;
    public EditText edtDataBoleto;

    public static PagamentoFragment newInstance() {
        PagamentoFragment fragment = new PagamentoFragment();
        return fragment;
    }
    //Relacionado ao spinner
    public Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        esconderActionBar(getActivity());

        //Spinner
        /*FragmentManager frg = getFragmentManager();
        PagamentoFragment pgtFragment = (PagamentoFragment) frg.findFragmentById(R.id.spn_parc_cartao_pag);*/


        //Relacionado ao spinner
        //spinner = (Spinner) getView().findViewById(R.id.spn_parc_cartao_pag);

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.parcelas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Spinner
        FragmentManager frg = getFragmentManager();
        PagamentoFragment pgtFragment = (PagamentoFragment) frg.findFragmentById(R.id.spn_parc_cartao_pag);


        //Relacionado ao spinner
        /*spinner = (Spinner) getView().findViewById(R.id.spn_parc_cartao_pag);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.parcelas_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pagamento, container, false);



        btnPagar = (Button) view.findViewById(R.id.btn_pagar_pag);
        btnPagar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                if(radioCartao.isChecked()){
                    Fragment pagRealizado = new PagRealizadoFragment();
                    openFragment(pagRealizado);
                }else{
                    Fragment pagRealizado = new PagRealizBoletoFragment();
                    openFragment(pagRealizado);
                }
            }
        });
        //return inflater.inflate(R.layout.fragment_pagamento, container, false);

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
        //String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        //SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        //String dateValue = edtDataBoleto.getText().toString().trim();
        /*SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new SimpleDateFormat("dd-MM-yyyy");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 5);
        date = c.getTime();

        String strDate = ft.format(date);
        Toast.makeText(getContext(), "A nova data de entrega é em " + strDate, Toast.LENGTH_SHORT).show();


        edtDataBoleto = (EditText) getView().findViewById(R.id.edt_data_boleto_pag);*/
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
    public static void mostrarActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().show();
    }
    public static void esconderActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().hide();
    }
}

