package com.example.diarioacme4.Modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diarioacme4.BD.DBHelper;
import com.example.diarioacme4.ListView.ItemListaDiario;
import com.example.diarioacme4.ListView.ListaUsuario;
import com.example.diarioacme4.R;

public class Usuario extends AppCompatActivity {
    Button btnComeçar;
    TextView txtQueridoDiario;
    DBHelper db;
    EditText edtMeuNome;
    ListaUsuario EditarUsuario, usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        btnComeçar = (Button) findViewById(R.id.btnComeçar);
        txtQueridoDiario = findViewById(R.id.txtQueridoDiario);
        edtMeuNome = findViewById(R.id.edtMeuNome);

        usuario = new ListaUsuario();
        db = new DBHelper(Usuario.this);


        btnComeçar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario.setNome(edtMeuNome.getText().toString());



                /**Passando dados do banco pela INTENT*/
                String meunome = usuario.getNome();
                Intent abrirDiario = new Intent(Usuario.this, MainActivity.class);
                startActivity(abrirDiario);

                Bundle bundle = new Bundle();

                bundle.putString("varMeuNome","Diário de "+ meunome );

                abrirDiario.putExtras(bundle);

                startActivity(abrirDiario);





            }
        });
    }
}
