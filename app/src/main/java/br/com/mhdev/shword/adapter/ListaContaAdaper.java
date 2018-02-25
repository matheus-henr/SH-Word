package br.com.mhdev.shword.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.mhdev.shword.CadastroSenhaActivity;
import br.com.mhdev.shword.ListaSenhaActivity;
import br.com.mhdev.shword.R;
import br.com.mhdev.shword.confg.ConexaoBD;
import br.com.mhdev.shword.model.Conta;
import br.com.mhdev.shword.repository.ContaRepository;

/**
 * Created by Matheus on 30/12/2017.
 */

public class ListaContaAdaper extends RecyclerView.Adapter<ListaContaAdaper.ViewHolderConta> {


    private List<Conta> dados;
    private Context context;
    private SQLiteDatabase conexao;

    public ListaContaAdaper(List<Conta> contas, Context context){
        dados = contas;
        this.context = context;
        conexao = new ConexaoBD(context).getConexao();
    }

    @Override
    public ViewHolderConta onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_lista_senhas,parent,false);

        ViewHolderConta holderConta = new ViewHolderConta(view);


        return holderConta;
    }

    @Override
    public void onBindViewHolder(ViewHolderConta holder, final int position) {
            if(dados != null && dados.size() > 0 ) {
                Conta conta = dados.get(position);
                holder.txtNome.setText(conta.getNome());
                holder.txtSenha.setText(conta.getSenha());
                holder.imbEditar.setOnClickListener(view -> updateItem(conta.getId(), position));
                holder.imbApagar.setOnClickListener(view ->  deletarItem(conta,position));
            }
    }

    private void deletarItem(Conta conta, int position) {
        ContaRepository repository = new ContaRepository(conexao);
        repository.apagar(conta.getId());
        dados.remove(conta);
        notifyItemChanged(position);
        Toast.makeText(context, "Apagado", Toast.LENGTH_SHORT).show();
    }

    private void updateItem(int contaID, int position) {
        Intent abrirTelaEditar = new Intent(context, CadastroSenhaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("conta", contaID);
        abrirTelaEditar.putExtras(bundle);
        context.startActivity(abrirTelaEditar);
        ContaRepository repository = new ContaRepository(conexao);
        dados = repository.buscar();
        notifyItemChanged(position);


    }

    

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public Conta getItem(int position){
        return dados.get(position);
    }

    public class ViewHolderConta extends RecyclerView.ViewHolder{

        public TextView txtNome;
        public TextView txtSenha;
        public ImageButton imbEditar;
        public ImageButton imbApagar;

        public ViewHolderConta(View itemView) {
            super(itemView);

            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtSenha = (TextView) itemView.findViewById(R.id.txtSenha);
            imbEditar = (ImageButton) itemView.findViewById(R.id.imbEditar);
            imbApagar = (ImageButton) itemView.findViewById(R.id.imbApagar);
        }
    }
}


