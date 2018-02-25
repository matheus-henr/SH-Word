package br.com.mhdev.shword;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.mhdev.shword.confg.ConexaoBD;
import br.com.mhdev.shword.model.Login;
import br.com.mhdev.shword.repository.LoginRepository;


public class MainActivity extends AppCompatActivity {

    private Login login;
    private SQLiteDatabase database;
    private LoginRepository loginRepository;
    private EditText campoSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        database = new ConexaoBD(this).getConexao();

        campoSenhaLogin = (EditText) findViewById(R.id.edtSenhaPrincipal);
        Button entrar = (Button) findViewById(R.id.btnEntrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirListaSenha();
            }
        });

        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cadastrarSenha) {
            cadastrarSenhaMaster();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarSenhaMaster(){
        if(usuarioCadastrado()){
            AlertDialog.Builder alert =  new AlertDialog.Builder(this);
            alert.setTitle("Ja existe senha cadastrada");
            alert.setMessage("Para cadastrar uma nova senha, limpe os dados do aplicativo na configuração do sistema");
            alert.setNeutralButton("Ok", null);
            alert.show();
        }else {
            Intent telaCadastroSenha = new Intent(this, senhaPrincipalActivity.class);
            startActivity(telaCadastroSenha);
        }
    }

    private boolean usuarioCadastrado(){
        loginRepository = new LoginRepository(database);
        login = loginRepository.buscar();
         if(login == null) return false;
          else return true;
    }

    private String senhaDigitada(){
        return campoSenhaLogin.getText().toString();
    }

    private void abrirListaSenha() {
        loginRepository = new LoginRepository(database);
        login = loginRepository.buscar();
        if(login != null) {
            if (login.getSenha().equals(senhaDigitada())) {
                campoSenhaLogin.setText("");
                Intent telaListaSenha = new Intent(this, ListaSenhaActivity.class);
                startActivity(telaListaSenha);
            } else if (senhaDigitada().equals((""))) {
                Toast.makeText(this, "campo senha vazio", Toast.LENGTH_SHORT).show();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Erro");
                alert.setMessage("Senha invalida");
                alert.setNeutralButton("Ok", null);
                alert.show();
                campoSenhaLogin.setText("");
            }
        }else{
            Toast.makeText(this, "Cadastre uma nova senha", Toast.LENGTH_SHORT).show();
        }

    }






}
