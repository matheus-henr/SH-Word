package br.com.mhdev.shword.confg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.mhdev.shword.confg.SqlScript.Script;

/**
 * Created by Matheus on 29/12/2017.
 */

public class BancoDeDadosConf  extends SQLiteOpenHelper{

    public BancoDeDadosConf(Context context){
        super(context, "shword", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Script.criarTabelaLogin());
        db.execSQL(Script.crirTabelaConta());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
