package br.com.silas.usercontroll.presentation.cadastro;

import dagger.Module;
import dagger.Provides;

@Module
public class CadastroModule {
    Contract.View mView;

    public CadastroModule(Contract.View mView) {
        this.mView = mView;
    }

    @Provides
    Contract.View providerCadastroVieww() {
        return mView;
    }
}
