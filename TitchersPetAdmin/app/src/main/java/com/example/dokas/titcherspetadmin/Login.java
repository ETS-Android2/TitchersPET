package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends Activity {

    private EditText nome;
    private EditText password;
    private HandlerBD ajudante;
    private SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        ajudante = new HandlerBD(this, "titchersPet");
        bd = ajudante.getWritableDatabase();
    }

    @Override
    protected void onStop(){

        super.onStop();
        bd.close();
    }

    @Override
    protected void onResume(){

        super.onResume();
        bd = ajudante.getWritableDatabase();
    }

    public void fazerLogin(View v){

        nome = (EditText) findViewById(R.id.nome_texto);
        password = (EditText) findViewById(R.id.pass_texto);

        try{

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] resultado = md.digest(password.getText().toString().getBytes());

            BigInteger aux = new BigInteger(1, resultado);

            String hexadecimal = aux.toString(16);

            while (hexadecimal.length() < 32)
                hexadecimal = "0" + hexadecimal;

            //procurar na base de dados pelo user e pass

            String query = "SELECT * FROM " + ajudante.TABLE_NAME1 + " WHERE " + ajudante.T1_COLUMN1 + "='" + nome.getText().toString() + "' AND " + ajudante.T1_COLUMN2 + "='" + hexadecimal + "';";
            Cursor procura = bd.rawQuery(query, null);

            //verificar se existe e se esta no sistema registado e autorizado

            if(procura.moveToFirst() && procura.getInt(3) == 1 && procura.getInt(2) == 1) {

                //passar parametros no intento
                Intent login = new Intent(this, Menu.class);
                login.putExtra("nome", procura.getString(0));
                startActivity(login);

            }else{

                Toast.makeText(this,"Username ou password incorretos!", Toast.LENGTH_SHORT).show();

            }

        }

        catch(NoSuchAlgorithmException n){}
    }
}
