package br.com.mhdev.shword.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import br.com.mhdev.shword.model.Login;

/**
 * Created by Matheus on 29/12/2017.
 */

public class LoginRepository {

    private SQLiteDatabase database;

    public LoginRepository(SQLiteDatabase database){

        this.database = database;
    }

    public void salvar(Login login){
        ContentValues db = new ContentValues();
        db.put("senha", login.getSenha());
        database.insertOrThrow("login",null,db);
    }

    public  Login buscar(){
        String[] id = {"1"};
        String script = "SELECT  id, senha FROM login WHERE id = ? ";
        Cursor cursor = database.rawQuery(script, id);

        Login login = new Login();

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            login.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            login.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("Senha")));
            return  login;
        } else return null;
    }

    public void editar(Login login) {
        String[] parms = {String.valueOf(login.getId())};

        ContentValues db = new ContentValues();
        db.put("Senha", login.getSenha());

        database.update("Conta",db,"id = ?", parms);

    }
}
