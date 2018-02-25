package br.com.mhdev.shword.util.validadores;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Matheus on 20/12/2017.
 */

public class Validador {


    public boolean isCampoVazio(String valor){
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }




}
