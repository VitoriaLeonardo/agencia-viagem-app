package com.cursoapp.skyblueapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cursoapp.skyblueapplication.Classes.Cliente;
import com.cursoapp.skyblueapplication.Classes.MeuIP;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    public TextView txtCadastrar;
    private EditText emailLogin;
    private EditText senhaLogin;
    private Button entrarLogin;
    private RequestQueue mQueue;
    private Cliente cliente = new Cliente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailLogin = (EditText) findViewById(R.id.edt_nome_login);
        senhaLogin = (EditText) findViewById(R.id.edt_senha_login);

        //TextView para cadastrar
        txtCadastrar = (TextView) findViewById(R.id.txt_cadastrar_login);
        txtCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroClienteActivity.class);
                startActivity(intent);
            }
        });

        mQueue = Volley.newRequestQueue(this);

        entrarLogin = (Button) findViewById(R.id.btn_entrar_login);
        entrarLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cliente.email = emailLogin.getText().toString();
                cliente.senha = senhaLogin.getText().toString();

                JSONObject body = new JSONObject(cliente.hashMap());

                //Colocar o nome da máquina e não o localhost
                String url = "http://192.168.0.103/api/login.php";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, body,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String resultado = response.getString("resultado");
                                    int status = response.getInt("status");
                                    if(status==200){
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        MeuIP.textButtom = "Sair";
                                    }else{
                                        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                                        emailLogin.setText("");
                                        senhaLogin.setText("");
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
                mQueue.add(request);

            }
        });
    }
    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
