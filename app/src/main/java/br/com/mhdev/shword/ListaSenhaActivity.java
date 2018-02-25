package br.com.mhdev.shword;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;


import java.util.List;

import br.com.mhdev.shword.adapter.ListaContaAdaper;
import br.com.mhdev.shword.confg.ConexaoBD;
import br.com.mhdev.shword.model.Conta;
import br.com.mhdev.shword.repository.ContaRepository;

public class ListaSenhaActivity extends AppCompatActivity {

    private RecyclerView lstConta;
    private SQLiteDatabase database;
    private ListaContaAdaper contaAdaper;
    private ImageButton imbEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_lista_senha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imbEditar = (ImageButton) findViewById(R.id.imbEditar);
        setSupportActionBar(toolbar);

        lstConta = (RecyclerView) findViewById(R.id.lstContas);
        database = new ConexaoBD(this).getConexao();


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        lstConta.setLayoutManager(layoutManager);

        lstConta.setHasFixedSize(true);

        contaAdaper = new ListaContaAdaper(buscar(),this);
        lstConta.setAdapter(contaAdaper);

        lstConta.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaCadastro = new Intent(ListaSenhaActivity.this, CadastroSenhaActivity.class);
                startActivity(telaCadastro);
            }
        });
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        contaAdaper = new ListaContaAdaper(buscar(),this);
        lstConta.setAdapter(contaAdaper);

    }

    private List<Conta> buscar(){
        ContaRepository repository = new ContaRepository(database);
        List<Conta> contas = repository.buscar();
        return contas;
    }








}
