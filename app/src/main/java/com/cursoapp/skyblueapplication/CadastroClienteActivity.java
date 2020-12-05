package com.cursoapp.skyblueapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class CadastroClienteActivity extends AppCompatActivity {
    public TextView txtEntrar;

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
    }
}
