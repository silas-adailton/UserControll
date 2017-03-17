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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class UserRepositorio implements Repositorio<Usuario> {
    DatabaseReference mdaDatabaseReference;

    @Inject
    public UserRepositorio(DatabaseReference databaseReference) {
        this.mdaDatabaseReference = databaseReference;
    }

    @Override
    public Observable saveUser(Usuario usuario) {

        return Observable.create(new ObservableOnSubscribe<Usuario>() {
            @Override
            public void subscribe(ObservableEmitter<Usuario> emitter) throws Exception {
                // mdaDatabaseReference.getDatabase().getReference("Users");
                mdaDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
                String userId = mdaDatabaseReference.push().getKey();

                mdaDatabaseReference.child(userId).setValue(usuario).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        emitter.onNext(usuario);
                        emitter.onComplete();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        emitter.onError(e);

                    }
                });
            }
        });
    }

    @Override
    public Observable<List<Usuario>> listUser() {
        mdaDatabaseReference = FirebaseDatabase.getInstance().getReference("Users");
        final List<Usuario> listUser = new ArrayList<>();

        return Observable.create(new ObservableOnSubscribe<List<Usuario>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Usuario>> emitter) throws Exception {
                mdaDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<Map<String, Usuario>> t = new GenericTypeIndicator<Map<String, Usuario>>() {
                        };

                        Map<String, Usuario> map = dataSnapshot.getValue(t);
                        for (String key : map.keySet()) {
                            listUser.add(map.get(key));
                            Log.d("Teste", "showListUser na UserRepositorio: " + key);
                        }

                        emitter.onNext(listUser);
                        emitter.onComplete();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        emitter.onError(databaseError.toException());

                    }
                });
            }
        });
    }
}
