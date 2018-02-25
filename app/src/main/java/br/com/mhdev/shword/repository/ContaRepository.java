package br.com.mhdev.shword.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.mhdev.shword.model.Conta;
import br.com.mhdev.shword.model.Login;

/**
 * Created by Matheus on 29/12/2017.
 */

public class ContaRepository {

    private SQLiteDatabase database;
    private Conta conta;


    public  ContaRepository(SQLiteDatabase database){

        this.database = database;
    }

    public void Salvar(Conta conta){
        ContentValues db = new ContentValues();
        db.put("Nome", conta.getNome());
        db.put("Senha",conta.getSenha());
        database.insertOrThrow("conta",null,db);
    }



    public List<Conta> buscar(){
        String sql = "Select id, nome, senha  from conta";
        Cursor cursor = database.rawQuery(sql, null);

        List<Conta> contas = new ArrayList<>();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Conta conta = new Conta();
                conta.setId(cursor.getInt(cursor.getColumnIndex("id")));
                conta.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                conta.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
                contas.add(conta);
            } while (cursor.moveToNext());
        }

        return contas;

    }

    public void apagar(Integer id){
        String[] parms = new String[1];
        parms[0] = String.valueOf(id);

        database.delete("conta", "id = ? ", parms);

    }

    public void editar(Conta conta) {
        String[] parms = {String.valueOf(conta.getId())};

        ContentValues db = new ContentValues();
        db.put("Nome", conta.getNome());
        db.put("Senha",conta.getSenha());

        database.update("Conta",db,"id = ?", parms);

    }

    public Conta buscarPorId(Integer contaId) {
        String[] id = {contaId.toString()};
        String script = "SELECT  id, nome, senha  FROM conta WHERE id = ? ";
        Cursor cursor = database.rawQuery(script, id);

        conta = new Conta();

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            conta.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            conta.setNome(cursor.getString(cursor.getColumnIndexOrThrow("nome")));
            conta.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));

        }
            return conta;
    }


    public Conta getConta() {
        return conta;
    }
}


