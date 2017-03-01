package br.com.silas.usercontroll.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.silas.usercontroll.ItemAdapter;
import br.com.silas.usercontroll.R;
import br.com.silas.usercontroll.Usuario;
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
    ItemAdapter adapter;
    List<Usuario> listUsers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarusuario);
        ButterKnife.bind(this);

        //listUsers = new Firebase().listUsers;

        progress.setVisibility(View.VISIBLE);

        if (listUsers.size() != 0) {
            progress.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);

            adapter = new ItemAdapter(ListarusuarioActivity.this, listUsers);

            recyclerView.setAdapter(adapter);
        } else {
            Toast.makeText(this, "A lista ainda esta vazia", Toast.LENGTH_SHORT).show();
        }

       /* reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, Usuario>> map = new GenericTypeIndicator<Map<String, Usuario>>() {
                };

                Map<String, Usuario> lista = dataSnapshot.getValue(map);
                for (String key : lista.keySet()) {
                    listUsers.add(lista.get(key));

                }

                adapter.notifyDataSetChanged();
                if (adapter != null){
                    progress.setVisibility(View.GONE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled: ",databaseError.toException() );

            }
        });
*/

    }

}



