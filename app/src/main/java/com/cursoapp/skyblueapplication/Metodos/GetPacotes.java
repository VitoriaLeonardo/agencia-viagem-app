package com.cursoapp.skyblueapplication.Metodos;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.cursoapp.skyblueapplication.Classes.Pacote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetPacotes {
    public List<Pacote> pacotesList = new ArrayList<>();
    public void getPacotes() {
        //getPacotes: buscar todos os pacotes, mas somente com as informações solicitadas para atribuir na home


        //Colocar o nome da máquina e não o localhost
        String url = "http://192.168.0.103/api/getPacotes.php";

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
                                pacote.valorUnitario = packagesJson.getInt("valorUnitario");

                                pacotesList.add(pacote);
                                /*
                                String tipoTransporte = packagesJson.getString("tipoTransporte");
                                String descricaoPac = packagesJson.getString("descricaoPacote");
                                   */
                                //String.valueOf(var)
                                /*Toast.makeText(getApplicationContext(), nomePac + ", " + tipoTransporte + ", " + descricaoPac, Toast.LENGTH_LONG).show();
                                mTextViewResult.append(nomePac + ", " + tipoTransporte + ", " + descricaoPac);*/
                            }
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
    }
}
