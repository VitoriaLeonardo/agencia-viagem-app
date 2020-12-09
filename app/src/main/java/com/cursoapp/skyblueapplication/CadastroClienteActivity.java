package com.cursoapp.skyblueapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.cursoapp.skyblueapplication.Adapter.PacoteAdapter;
import com.cursoapp.skyblueapplication.Classes.Cliente;
import com.cursoapp.skyblueapplication.Classes.Pacote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class CadastroClienteActivity extends AppCompatActivity {
    public TextView txtEntrar;
    private RequestQueue mQueue;
    private Cliente cliente = new Cliente();
    private EditText nomeCad;
    private EditText cpfCad;
    private EditText telCad;
    private EditText emailCad;
    private EditText senhaCad;
    private EditText cepCad;
    private EditText numeroCad;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        txtEntrar = (TextView) findViewById(R.id.txt_entrar_login);
        txtEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroClienteActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        nomeCad = (EditText) findViewById(R.id.edt_nome_cad_cli);
        emailCad = (EditText) findViewById(R.id.edt_email_cad_cli);
        telCad = (EditText) findViewById(R.id.edt_tel_cad_cli);
        cpfCad = (EditText) findViewById(R.id.edt_cpf_cli);
        senhaCad = (EditText) findViewById(R.id.edt_senha_cad_cli);

        cepCad = (EditText) findViewById(R.id.edt_cep_cad_cli);
        numeroCad = (EditText) findViewById(R.id.edt_n_cad_cli);

        cadastrar = (Button) findViewById(R.id.btn_cadastrar_login);

        //Para setar
        mQueue = Volley.newRequestQueue(this);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cliente.nome = nomeCad.getText().toString();
                cliente.cpf = cpfCad.getText().toString();
                cliente.rg = "01.102.160-5";
                cliente.telefone = telCad.getText().toString();
                cliente.email = emailCad.getText().toString();
                cliente.senha = senhaCad.getText().toString();

                JSONObject body = new JSONObject(cliente.hashMap());

                //Colocar o nome da máquina e não o localhost
                String url = "http://192.168.0.103/api/createCliente.php";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String resultado = response.getString("resultado");


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

                nomeCad.setText("");
                cpfCad.setText("");
                telCad.setText("");
                emailCad.setText("");
                senhaCad.setText("");
                cepCad.setText("");
                numeroCad.setText("");

                Intent intent = new Intent(CadastroClienteActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}