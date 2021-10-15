package com.example.diarioacme4.Modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diarioacme4.BD.DBHelper;
import com.example.diarioacme4.ListView.ItemListaDiario;
import com.example.diarioacme4.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    DBHelper db;
    ArrayList<ItemListaDiario> lstDiario;
    ItemListaDiario meudiario;
    ArrayAdapter adapter;
    Button btnEscrever, btnVoltar;
    TextView txtQueridoDiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnEscrever = (Button) findViewById(R.id.btnEscrever);
        txtQueridoDiario = (TextView) findViewById(R.id.txtQueridoDiario);
        btnVoltar = findViewById(R.id.btnVoltar);

        Intent receberDados = getIntent();
        Bundle bundle = receberDados.getExtras();

        txtQueridoDiario.setText(bundle.getString( "varMeuNome"));


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent fecharDiario = new Intent(MainActivity.this, Usuario.class);
                startActivity(fecharDiario);
                MainActivity.this.finish();
            }
        });
        btnEscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent abrirDiario = new Intent(MainActivity.this, Escrever_no_diario.class);
                startActivity(abrirDiario);
            }
        });

        lista = (ListView) findViewById(R.id.lstDiario);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                ItemListaDiario paginaEscolhida = (ItemListaDiario) adapter.getItemAtPosition(position);

                Intent i = new Intent(MainActivity.this, Escrever_no_diario.class);
                i.putExtra("pagina-escolhida", paginaEscolhida);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {

                meudiario = (ItemListaDiario) adapter.getItemAtPosition(position);

                return false;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Clique novamente para rasgar!");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                db = new DBHelper(MainActivity.this);
                db.DeletarDiario(meudiario);
                db.close();

                carregarPaginas();

                return true;
            }
        });
    }

    protected void onResume() {
        super.onResume();
        carregarPaginas();
    }


    public void carregarPaginas(){
        db = new DBHelper(MainActivity.this);
        lstDiario = db.getLista();
        db.close();

        if(lstDiario != null){
            adapter = new ArrayAdapter<ItemListaDiario>(MainActivity.this, android.R.layout.simple_list_item_1, lstDiario);
            lista.setAdapter(adapter);
        }
    }
}
