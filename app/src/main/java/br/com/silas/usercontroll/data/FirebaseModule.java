package br.com.silas.usercontroll.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    @Provides
    DatabaseReference providerGetDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    Repository providerGetRepository(DatabaseReference databaseReference) {
        return new Firebase(databaseReference);
    }
}
