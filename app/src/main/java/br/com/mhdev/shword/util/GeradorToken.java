package br.com.mhdev.shword.util;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Matheus on 06/01/2018.
 */

public class GeradorToken {

    public Integer gerar(String senha){
        //xxxxx ou xxxxxx
        StringBuilder token = new StringBuilder();
        token.append(getTime());//xx
        token.append(numeroAleatorio());//x
        token.append(senha.length());// x ou xx
        token.append(numeroAleatorio());//x
        return Integer.parseInt(token.toString());
    }

    private int getTime(){
        Calendar data = Calendar.getInstance();
        return data.getTime().getSeconds();
    }

    private int numeroAleatorio(){
        Random random = new Random();
        int x =  1 + random.nextInt(9);
        return x;
    }


}
