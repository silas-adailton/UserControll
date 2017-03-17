package br.com.silas.usercontroll.data;

import dagger.Component;

@Component(modules = {FirebaseModule.class})
public interface RepositoryComponent {

    Repository getRepository();

    Repositorio getRepositorio();
}
