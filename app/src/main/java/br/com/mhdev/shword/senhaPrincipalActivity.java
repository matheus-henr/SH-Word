package br.com.mhdev.shword;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.mhdev.shword.confg.ConexaoBD;
import br.com.mhdev.shword.model.Login;
import br.com.mhdev.shword.repository.LoginRepository;
import br.com.mhdev.shword.util.GeradorToken;
import br.com.mhdev.shword.util.validadores.Validador;

public class senhaPrincipalActivity extends AppCompatActivity {

    private SQLiteDatabase dataBase;
    private LoginRepository loginRepository;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_senha_principal);


        dataBase = new ConexaoBD(this).getConexao();
        campoSenha = (EditText) findViewById(R.id.edtCadastroSenha);

        super.onCreate(savedInstanceState);
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
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void salvarSenha(){
        Login login = pegarDados();
        if(validarSenhaMaster(login)) {
            loginRepository = new LoginRepository(dataBase);
            loginRepository.salvar(login);
            finish();
        }
    }

    private Login pegarDados(){
        Login login = new Login();
        login.setSenha(campoSenha.getText().toString());

        return login;
    }

    public boolean validarSenhaMaster(Login login){
        Validador validador = new Validador();
        if(validador.isCampoVazio(login.getSenha()) && validador.isCampoVazio(login.getSenha())) {
            Toast.makeText(this,"Campo senha não pode estar vazio", Toast.LENGTH_SHORT);
            return false;
        }
        else if(login.getSenha().length() <=4){
            Toast.makeText(this,"Senha muito curta", Toast.LENGTH_SHORT);
            return false;
        }
        else{
            return true;
        }
    }


}
