package com.example.proyecto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class Edit_and_Delete extends AppCompatActivity implements View.OnClickListener{

    Button botonElminar;
    SQLiteDatabase sql;
    Usuario u;
    daoUsuario dao;
    int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_and_delete);

        botonElminar = (Button) findViewById(R.id.buttonEliminar);

        botonElminar.setOnClickListener(this);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        dao=new daoUsuario(this);
        u=dao.getUserById(id);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonEliminar:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setMessage("¿Está seguro que desea eliminar la cuenta? UnU");
                b.setCancelable(false);
                b.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (deleteUsuario(id)){
                            Log.d("ELIM","Logré eliminar el usuario xd");
                            Toast.makeText(Edit_and_Delete.this,"Usuario eliminado",Toast.LENGTH_LONG).show();
                            Intent i5 = new Intent(Edit_and_Delete.this, Login.class);
                            startActivity(i5);
                            finish();
                        }else{
                            Log.d("ELIM","No logré eliminar el usuario XD");
                            Toast.makeText(Edit_and_Delete.this,"ERROR: No se pudo borrar nada",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.show();
                break;
        }
    }

    public boolean deleteUsuario(int id){
        return (sql.delete("usuario","id="+id,null)>0);
    }


}