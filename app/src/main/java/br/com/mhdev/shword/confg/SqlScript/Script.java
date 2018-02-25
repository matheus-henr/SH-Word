package br.com.mhdev.shword.confg.SqlScript;

/**
 * Created by Matheus on 29/12/2017.
 */

public class Script{

    public static String  criarTabelaLogin(){
        StringBuffer sql = new StringBuffer();
        sql.append("Create Table IF  NOT  EXISTS login ( ");
        sql.append("id INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("Senha VARCHAR (30) NOT NULL DEFAULT('') );");
        System.out.print(sql.toString());

        return  sql.toString();
    }

    public static String crirTabelaConta(){
        StringBuffer sql = new StringBuffer();
        sql.append("Create Table IF NOT EXISTS conta ( ");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("nome VARCHAR(50) NOT NULL DEFAULT(''), ");
        sql.append("senha VARCHAR(30) NOT NULL DEFAULT('') )");

        return sql.toString();
    }

}
