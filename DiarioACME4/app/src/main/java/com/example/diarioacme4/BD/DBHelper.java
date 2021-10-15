package com.example.diarioacme4.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.diarioacme4.ListView.ItemListaDiario;
import com.example.diarioacme4.ListView.ListaUsuario;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private  static final String DATABASE = "MeuDiario";
    private static final int VERSION = 1;



    public DBHelper(Context context){
        super(context, DATABASE, null, VERSION);
    }
    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tblMeuDiario(id integer primary key autoincrement not null, " +
                "Titulo text not null,Conteudo text not null,PalavraSecreta text);");
        db.execSQL("CREATE TABLE tblUsuarioTemporario(id integer primary key autoincrement not null, " +
                "Nome text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblMeuDiario");
        db.execSQL("DROP TABLE IF EXISTS tblUsuarioTemporario");

    }
    //
    public void salvarDiario(ItemListaDiario meudiario){
        ContentValues dados = new ContentValues();

        dados.put("Titulo", meudiario.getTitulo());
        dados.put("Conteudo", meudiario.getConteudo());
        dados.put("PalavraSecreta", meudiario.getPalavraSecreta());
        getWritableDatabase().insert("tblMeuDiario", null, dados);
    }

    public void salvarUsuario(ListaUsuario usuario){
        ContentValues dados = new ContentValues();

        dados.put("Nome", usuario.getNome());
        getWritableDatabase().insert("tblUsuarioTemporario", null, dados);
    }

    public void EditarDiario(ItemListaDiario meudiario){
        ContentValues dados = new ContentValues();

        dados.put("Titulo", meudiario.getTitulo());
        dados.put("Conteudo", meudiario.getConteudo());
        dados.put("PalavraSecreta", meudiario.getPalavraSecreta());
        String[] args = {meudiario.getID().toString()};
        getWritableDatabase().update("tblMeuDiario", dados, "id=?", args);
    }

    public void DeletarDiario (ItemListaDiario meudiario){
        String[] args = {meudiario.getID().toString()};

        getWritableDatabase().delete("tblMeuDiario", "id=?", args);
    }

    public ArrayList<ItemListaDiario> getLista(){
        String[] columns = {"id", "Titulo","Conteudo","PalavraSecreta"};

        Cursor cursor = getWritableDatabase().query("tblMeuDiario", columns, null, null, null, null, null, null );

        ArrayList<ItemListaDiario> paginas = new ArrayList<>();

        while(cursor.moveToNext()){
            ItemListaDiario pagina = new ItemListaDiario();

            pagina.setID(cursor.getLong(0));
            pagina.setTitulo(cursor.getString(1));
            pagina.setConteudo(cursor.getString(2));
            pagina.setPalavraSecreta(cursor.getString(3));

            paginas.add(pagina);
        }
        return paginas;
    }

    public ArrayList<ListaUsuario> getLista2(){
        String[] columns2 = {"id", "Nome"};

        Cursor cursor2 = getWritableDatabase().query("tblUsuarioTemporario", columns2, null, null, null, null, null, null );

        ArrayList<ListaUsuario> usuarios = new ArrayList<>();

        while(cursor2.moveToNext()){
            ListaUsuario usuario = new ListaUsuario();

            usuario.setID(cursor2.getLong(0));
            usuario.setNome(cursor2.getString(1));


            usuarios.add(usuario);
        }
        return usuarios;
    }
}
