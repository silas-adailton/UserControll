package br.com.silas.usercontroll.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.silas.usercontroll.R;
import br.com.silas.usercontroll.Usuario;
import br.com.silas.usercontroll.presentation.cadastro.CadastroRowAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListarusuarioActivity extends AppCompatActivity {
    public static final String TAG = "br.com.silas";

    @BindView(R.id.recycler_view_usuario)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar2)
    ProgressBar progress;
    FirebaseDatabase database;
    DatabaseReference reference;
    CadastroRowAdapter adapter;
    List<Usuario> listUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarusuario);
        ButterKnife.bind(this);


    }

}



