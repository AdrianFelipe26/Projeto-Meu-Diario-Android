package com.example.diarioacme4.Modelo;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diarioacme4.BD.DBHelper;
import com.example.diarioacme4.ListView.ItemListaDiario;
import com.example.diarioacme4.R;
import com.example.diarioacme4.BD.DBHelper;
import com.example.diarioacme4.ListView.ItemListaDiario;
import com.example.diarioacme4.R;


public class Escrever_no_diario extends AppCompatActivity {

    Button btnVoltar, btnEscrever, btnTerminei;
    EditText edtTituloDiario,edtConteudoDiario,edtPalavraSecreta, edtVerificarSenha;
    RadioButton rdbNormal, rdbSegredo;
    DBHelper db;
    TextView txtTitulo, txtConteudo, txtVerificarSenha;


    ItemListaDiario EditarDiario, meudiario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escrever_no_diario);



        meudiario = new ItemListaDiario();
        db = new DBHelper(Escrever_no_diario.this);

        Intent intent = getIntent();
        EditarDiario = (ItemListaDiario) intent.getSerializableExtra("pagina-escolhida");


        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnEscrever = (Button) findViewById(R.id.btnEscrever);
        btnTerminei = (Button) findViewById(R.id.btnTerminei);

        edtTituloDiario = (EditText) findViewById(R.id.edtTituloDiario);
        edtConteudoDiario = (EditText) findViewById(R.id.edtConteudoDiario);
        edtPalavraSecreta = (EditText) findViewById(R.id.edtPalavraSecreta);
        edtVerificarSenha = (EditText) findViewById(R.id.edtVerificarSenha);


        txtTitulo = (TextView) findViewById(R.id.txtTituloDiario);
        txtConteudo = (TextView) findViewById(R.id.txtConteudoDiario);
        txtVerificarSenha = (TextView) findViewById(R.id.txtVerificarSenha);



        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Escrever_no_diario.this.finish();
            }
        });



        if(EditarDiario != null){
            btnTerminei.setText("Reescrever");

            /*edtTituloDiario.setText(EditarDiario.getTitulo());
            edtConteudoDiario.setText(EditarDiario.getConteudo());*/


            /*LÓGICA PARA BLOQUEAR OS CAMPOS*/

            edtVerificarSenha.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String PalavraSecreta = EditarDiario.getPalavraSecreta();
                    String VerificarSenha = edtVerificarSenha.getText().toString();



                    if(VerificarSenha.equals(PalavraSecreta)
                    ){


                        edtTituloDiario.setText(EditarDiario.getTitulo());
                        edtConteudoDiario.setText(EditarDiario.getConteudo());
                        meudiario.setTitulo(edtTituloDiario.getText().toString());
                        meudiario.setConteudo(edtConteudoDiario.getText().toString());
                        edtTituloDiario.setEnabled(true);
                        edtConteudoDiario.setEnabled(true);
                        edtPalavraSecreta.setEnabled(true);
                        edtPalavraSecreta.setText(EditarDiario.getPalavraSecreta());


                    }
                    /*if(edtVerificarSenha.getText().length() > 0){


                        edtTituloDiario.setText(EditarDiario.getTitulo());
                        edtConteudoDiario.setText(EditarDiario.getConteudo());
                        meudiario.setTitulo(edtTituloDiario.getText().toString());
                        meudiario.setConteudo(edtConteudoDiario.getText().toString());
                        edtTituloDiario.setEnabled(true);
                        edtConteudoDiario.setEnabled(true);


                    }*/
                    else{

                        edtTituloDiario.setText("-Conteudo Protegido por segurança, coloque a senha para desbloquear-");
                        edtTituloDiario.setEnabled(false);
                        edtConteudoDiario.setText("-Conteudo Protegido por segurança, coloque a senha para desbloquear-");
                        edtConteudoDiario.setEnabled(false);
                        edtPalavraSecreta.setEnabled(false);
                        edtVerificarSenha.setEnabled(true);
                        edtPalavraSecreta.setHint("??????????????");
                    }
                }


                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            edtTituloDiario.setText("-Conteudo Protegido por segurança, coloque a senha para desbloquear-");
            edtTituloDiario.setEnabled(false);
            edtConteudoDiario.setText("-Conteudo Protegido por segurança, coloque a senha para desbloquear-");
            edtConteudoDiario.setEnabled(false);
            edtPalavraSecreta.setEnabled(false);
            edtPalavraSecreta.setHint("??????????????");


            meudiario.setID(EditarDiario.getID());
            edtVerificarSenha.setVisibility(View.VISIBLE);
            txtVerificarSenha.setVisibility(View.VISIBLE);
        }
        else{
            btnTerminei.setText("Terminei!");
        }

        btnTerminei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meudiario.setTitulo(edtTituloDiario.getText().toString());
                meudiario.setConteudo(edtConteudoDiario.getText().toString());
                meudiario.setPalavraSecreta(edtPalavraSecreta.getText().toString());


                if(btnTerminei.getText().toString().equals("Terminei!")){
                    db.salvarDiario(meudiario);
                    edtVerificarSenha.setVisibility(View.INVISIBLE);
                    txtVerificarSenha.setVisibility(View.INVISIBLE);

                    db.close();

                    Escrever_no_diario.this.finish();
                }
                else {

                    db.EditarDiario(meudiario);
                    db.close();
                    Escrever_no_diario.this.finish();
                }
            }
        });
    }
}
