package br.com.silas.usercontroll.presentation.cadastro;

import br.com.silas.usercontroll.MainComponent;
import br.com.silas.usercontroll.data.RepositoryComponent;
import dagger.Component;

@Component(dependencies = {MainComponent.class, RepositoryComponent.class}, modules = {CadastroModule.class})
public interface CadastroComponent {
    void inject(CadastroActivity cadastroActivity);

}
