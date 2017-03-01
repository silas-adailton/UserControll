package br.com.silas.usercontroll.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.com.silas.usercontroll.Usuario;

public class Firebase implements Repository<Usuario> {
    List<Usuario> listUsers = new ArrayList<>();
    private DatabaseReference mDatabaseReference;

    @Inject
    public Firebase(DatabaseReference databaseReference) {
        this.mDatabaseReference = databaseReference;
    }

    public Firebase() {
    }

    @Override
    public void save(Usuario usuario, final RepositoryExecutor repositoryExecutor) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
        String userId = mDatabaseReference.push().getKey();
        mDatabaseReference.child(userId).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                repositoryExecutor.onSuccess(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                repositoryExecutor.onError(e);
            }
        });
    }

    @Override
    public void toList(final RepositoryExecutor repositoryExecutor) {
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, Usuario>> map = new GenericTypeIndicator<Map<String, Usuario>>() {
                };
                Map<String, Usuario> lista = dataSnapshot.getValue(map);

                for (String key : lista.keySet()) {
                    listUsers.add(lista.get(key));
                    Log.d("onDataChange: ", lista.get(key).toString());

                }

                // Log.d("onDataChange: ",listUsers.toString());
                repositoryExecutor.onList(listUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled: ", databaseError.getMessage());

            }
        });

    }

    /*@Override
    public List<Usuario> toList() {

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");

        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<Map<String, Usuario>> map = new GenericTypeIndicator<Map<String, Usuario>>() {
                };

                Map<String, Usuario> lista = dataSnapshot.getValue(map);
                for (String key : lista.keySet()) {
                    listUsers.add(lista.get(key));

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("onCancelled: ", databaseError.toException());

            }
        });

        return listUsers;

    }
*/

    @Override
    public List<Usuario> findAllByNome(Usuario usuario) {
        return null;
    }

    @Override
    public void update(Usuario usuario) {

    }

    @Override
    public void delete(Usuario usuario) {

    }
}
