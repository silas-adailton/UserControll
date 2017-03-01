package br.com.silas.usercontroll;

import android.app.Application;

import br.com.silas.usercontroll.data.DaggerRepositoryComponent;
import br.com.silas.usercontroll.data.FirebaseModule;
import br.com.silas.usercontroll.data.RepositoryComponent;


public class MainApplication extends Application {
    private static MainComponent sMainComponent;
    private static RepositoryComponent sRepositoryComponent;

    public static MainComponent getsMainComponent() {
        return sMainComponent;
    }

    public static RepositoryComponent getsRepository() {
        return sRepositoryComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sMainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(getApplicationContext()))
                .build();

        sRepositoryComponent = DaggerRepositoryComponent.builder()
                .firebaseModule(new FirebaseModule())
                .build();

    }
}
