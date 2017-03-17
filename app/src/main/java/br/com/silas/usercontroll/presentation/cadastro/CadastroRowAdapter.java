package br.com.silas.usercontroll.presentation.cadastro;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.silas.usercontroll.R;
import br.com.silas.usercontroll.Usuario;


public class CadastroRowAdapter extends RecyclerView.Adapter<CadastroRowAdapter.MyViewHolder> {
    private List<Usuario> list;

    public CadastroRowAdapter(List<Usuario> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_listview_layout, parent, false);
        //MyViewHolder holder = new MyViewHolder(view);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyViewHolder viewHolder = holder;
        Usuario usuario = (Usuario) list.get(position);

        viewHolder.textViewNome.setText(usuario.getmNome());
        viewHolder.textViewLogin.setText(usuario.getmNome());
        viewHolder.textViewSenha.setText(usuario.getmSenha());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome;
        TextView textViewLogin;
        TextView textViewSenha;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewNome = (TextView) itemView.findViewById(R.id.text_item_nome);
            textViewLogin = (TextView) itemView.findViewById(R.id.text_item_login);
            textViewSenha = (TextView) itemView.findViewById(R.id.text_item_senha);
        }


    }
}
