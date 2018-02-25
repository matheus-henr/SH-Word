package br.com.mhdev.shword.confg;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;

/**
 * Created by Matheus on 29/12/2017.
 */

public class ConexaoBD {

    private SQLiteDatabase conexao;
    private BancoDeDadosConf bancoDeDados;

    private Context contexto;

    public ConexaoBD(Context contexto){
        this.contexto = contexto;
    }





    public SQLiteDatabase getConexao(){
        try {
            bancoDeDados = new BancoDeDadosConf(contexto);
            conexao = bancoDeDados.getWritableDatabase();
            return conexao;
        } catch (SQLException ex) {
            AlertDialog.Builder bd = new AlertDialog.Builder(contexto);
            bd.setTitle("Erro");
            bd.setMessage(ex.getMessage());
            bd.setNeutralButton("Ok", null);
            bd.show();
            ex.printStackTrace();
            System.out.print(ex.getMessage());

            return null;
        }
    }

}
