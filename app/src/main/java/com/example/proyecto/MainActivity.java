package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonView, buttonSalir;
    TextView NombreUsuario;
    int id=0;
    Usuario u;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        buttonView = (Button) findViewById(R.id.buttonVistaBD);
        buttonSalir = (Button) findViewById(R.id.buttonSalir);
        NombreUsuario = (TextView) findViewById(R.id.NombreUsuario);
        buttonView.setOnClickListener(this);
        buttonSalir.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuario(this);
        u=dao.getUserById(id);
        NombreUsuario.setText(u.getNombre());


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSalir:
                Intent i3 = new Intent(MainActivity.this, Login.class);
                startActivity(i3);
                break;

            case R.id.buttonVistaBD:
                Intent i4 = new Intent(MainActivity.this, View.class);
                startActivity(i4);
                break;


        }
    }
}