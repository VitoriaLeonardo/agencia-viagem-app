package com.cursoapp.skyblueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cursoapp.skyblueapplication.Adapter.PacoteAdapter;
import com.cursoapp.skyblueapplication.Classes.MeuIP;
import com.cursoapp.skyblueapplication.Classes.Pacote;
import com.cursoapp.skyblueapplication.Metodos.GetPacote;
import com.cursoapp.skyblueapplication.Metodos.GetPacotes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private Button buttonInfo1;
    private Button buttonInfo2;
    private Button buttonT2;
    public NumberPicker numberPicker;

    //RecyclerView
    private RecyclerView rvPacotes;
    private RecyclerView.LayoutManager mng;
    private RecyclerView.Adapter adp;
    private Button btnVerPac;
    public List<Pacote> listPac= new ArrayList<>();
    private RequestQueue mQueue;


    public static Fragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Método getPacotes
        GetPacotes getPacotesMethod = new GetPacotes();
        getPacotesMethod.getPacotes();

        mostrarActionBar(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //RecyclerView
        rvPacotes = (RecyclerView) view.findViewById(R.id.rv_lista_pacotes);
        rvPacotes.setHasFixedSize(true);

        mng = new LinearLayoutManager(getContext());
        rvPacotes.setLayoutManager(mng);


        //Colocar o nome da máquina e não o localhost
        String url = "http://"+ MeuIP.ip +"/api/getPacotes.php";

        //Para setar
        mQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("packages");

                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject packagesJson = jsonArray.getJSONObject(i);

                                Pacote pacote = new Pacote();
                                pacote.id = packagesJson.getInt("id");
                                pacote.nome = packagesJson.getString("nome");
                                pacote.descricao = packagesJson.getString("descricaoPacote");
                                pacote.imagem = packagesJson.getString("imagem");
                                pacote.valorUnitario = (float) packagesJson.getDouble("valorUnitario");

                                listPac.add(pacote);

                            }
                            adp = new PacoteAdapter(listPac, getActivity());
                            rvPacotes.setAdapter(adp);
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

        return view;
    }

    //Abrindo Fragment
    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void mostrarActionBar(Activity parent) {
        MainActivity mainActivity = (MainActivity) parent;
        mainActivity.getSupportActionBar().show();
    }
}
