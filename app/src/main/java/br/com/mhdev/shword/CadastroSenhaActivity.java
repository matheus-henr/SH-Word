package br.com.mhdev.shword;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.mhdev.shword.confg.ConexaoBD;
import br.com.mhdev.shword.model.Conta;
import br.com.mhdev.shword.repository.ContaRepository;
import br.com.mhdev.shword.util.validadores.Validador;

public class CadastroSenhaActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoSenha;
    private SQLiteDatabase dataBase;
    private ContaRepository contaRepository;
    private Conta conta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cadastro_senha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoNome = (EditText)  findViewById(R.id.edtContaNome);
        campoSenha = (EditText) findViewById(R.id.edtContaSenha);

        dataBase = new ConexaoBD(this).getConexao();

        pegarDadosEditar();

        super.onCreate(savedInstanceState);
    }

    private void pegarDadosEditar() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            Integer contaId = bundle.getInt("conta");
            if (contaId != null) {
                contaRepository = new ContaRepository(dataBase);
                 contaRepository.buscarPorId(contaId);
                 conta = contaRepository.getConta();
                if (conta != null) prencherFormulario(conta);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_confirmar:
                salvarSenha();
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarSenha(){
        pegarDadosTela();
        if(validar() == true){
            contaRepository = new ContaRepository(dataBase);
            if(conta.getId() != null){
                contaRepository.editar(conta);
                finish();
            }else {
                contaRepository.Salvar(conta);
                finish();
            }
        }
    }

    private boolean validar(){
        boolean validoNome = true;
        boolean validoSenha = true;
        boolean valido = false;
        Validador validador = new Validador();
        if(conta != null){
            if(validador.isCampoVazio(conta.getNome()) == true){
                 validoNome = false;
             }
             if(validador.isCampoVazio(conta.getSenha())){
                validoSenha = false;
             }
        }else{
            validoNome = false;
            validoSenha = false;
        }

        if(validoNome == true & validoSenha == true){
            valido = true;
        }


        return valido;
    }

    public void pegarDadosTela(){
        if(conta == null) conta = new Conta();
        conta.setNome(campoNome.getText().toString());
        conta.setSenha(campoSenha.getText().toString());
    }

    private void prencherFormulario(Conta contaEditar) {
        campoNome.setText(contaEditar.getNome());
        campoSenha.setText(contaEditar.getSenha());
    }

}



