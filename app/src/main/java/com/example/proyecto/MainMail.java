package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainMail extends AppCompatActivity {

    public EditText mEmail;
    public EditText mSubject;
    public EditText mMessage;
    daoUsuario my_daoUsuario;
    Usuario user;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEmail = (EditText)findViewById(R.id.mailID);
        mMessage = (EditText)findViewById(R.id.messageID);
        mSubject = (EditText)findViewById(R.id.subjectID);

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        my_daoUsuario=new daoUsuario(this);
        user=my_daoUsuario.getUserById(id);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    //case R.id.Enviar:
                    case R.id.fab:
                        sendMail();
                        /*Intent mainActivity = new Intent(MainMail.this, MainActivity.class);
                        mainActivity.putExtra("id",user.getId());
                        startActivity(mainActivity);*/
                        break;
                }

            }
        });
    }
    private void sendMail() {

        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString();
        String subject = mSubject.getText().toString().trim();


        JavaMailAPI javaMailAPI = new JavaMailAPI(this,mail,subject,message);

        javaMailAPI.execute();

    }

}
