package br.com.silas.usercontroll;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule extends MainApplication {
    private final Context mContext;

    public MainModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    Context providerContext() {
        return mContext;
    }
}
