package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {
    EditText nom, correo, pass;
    Button confirmar, cancelar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);
        nom = (EditText) findViewById(R.id.Nombre);
        correo = (EditText) findViewById(R.id.Correo);
        pass = (EditText) findViewById(R.id.Pass);

        confirmar = (Button) findViewById(R.id.buttonConfirm);
        cancelar = (Button) findViewById(R.id.buttonCancelar);
        confirmar.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        dao = new daoUsuario(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonConfirm:
                Usuario u=new Usuario();
                u.setNombre(nom.getText().toString());
                u.setCorreo(correo.getText().toString());
                u.setPassword(pass.getText().toString());

                if(!u.isNull()){
                    Toast.makeText(this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                }else if (dao.insertUsuario(u)){
                    Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(Registro.this, Login.class);
                    startActivity(i2);
                    finish();
                         }else{
                    Toast.makeText(this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.buttonCancelar:
                Intent i = new Intent(Registro.this, Login.class);
                startActivity(i);
                break;

        }
        }
    }
