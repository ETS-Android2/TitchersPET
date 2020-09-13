package com.example.dokas.titcherspetadmin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Menu extends Activity {

    private TextView nome;
    private String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu);

        nome = (TextView) findViewById(R.id.nome);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        String data_aux = sdf2.format(new Date());
        TextView data = (TextView) findViewById(R.id.data);
        data.setText(data_aux);

        try{
            Intent recebido = getIntent();
            nome.setText(recebido.getStringExtra("nome"));
        }

        catch(Exception e){}

    }

    public void pedidos(View v){

        Intent muda = new Intent(this, pedidos.class);
        startActivity(muda);

    }

    public void util(View v){

        Intent muda = new Intent(this, utilizadores.class);
        startActivity(muda);

    }

    public void stats(View v){

        Intent muda = new Intent(this, estatisticas.class);
        startActivity(muda);

    }

    public void logout(View v){

        this.finish();

    }


}
