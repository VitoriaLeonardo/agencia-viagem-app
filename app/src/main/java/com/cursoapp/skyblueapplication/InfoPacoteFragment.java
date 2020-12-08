package com.cursoapp.skyblueapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cursoapp.skyblueapplication.Classes.Pacote;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class InfoPacoteFragment extends Fragment {
    public NumberPicker numberPicker;
    public NumberPicker numberPicker2;
    public NumberPicker numberPicker3;
    public Button btnComprar;
    public Pacote pacote = new Pacote();
    private RequestQueue mQueue;

    public InfoPacoteFragment() {
        // Required empty public constructor
    }

    public static InfoPacoteFragment newInstance(String param1, String param2) {
        InfoPacoteFragment fragment = new InfoPacoteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_info_pacote, container, false);

        mQueue = Volley.newRequestQueue(getContext());

        btnComprar = (Button) view.findViewById(R.id.btn_comprar_pacote);
        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment pagamento = new PagamentoFragment();
                openFragment(pagamento);
            }
        });

        //Get
        String url = "http://192.168.0.103/api/getPacote.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            pacote.id = response.getInt("id");
                            pacote.valorUnitario = response.getInt("valorUnitario");
                            pacote.nome = response.getString("nome");
                            pacote.tipoTransporte = response.getString("tipoTransporte");
                            pacote.descricao = response.getString("descricaoPacote");
                            pacote.dataIda = response.getString("dataIda");
                            pacote.dataVolta = response.getString("dataVolta");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
        return inflater.inflate(R.layout.fragment_info_pacote, container, false);

    }

    public static void esconderActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().hide();
    }

    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {

        NumberPicker numberPickerAdult = (NumberPicker) getView().findViewById(R.id.numberpicker);
        numberPickerAdult.setMinValue(1);
        numberPickerAdult.setMaxValue(10);
        numberPickerAdult.setWrapSelectorWheel(true);
        numberPickerAdult.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {

            }
        });

        NumberPicker numberPickerCrianca = (NumberPicker) getView().findViewById(R.id.numberpicker2);
        numberPickerCrianca.setMinValue(0);
        numberPickerCrianca.setMaxValue(10);
        numberPickerCrianca.setWrapSelectorWheel(true);
        numberPickerCrianca.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
            }
        });


        NumberPicker numberPickerQuarto = (NumberPicker) getView().findViewById(R.id.numberpicker3);
        numberPickerQuarto.setMinValue(1);
        numberPickerQuarto.setMaxValue(4);
        numberPickerQuarto.setWrapSelectorWheel(true);
        numberPickerQuarto.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
            }
        });
    }
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
