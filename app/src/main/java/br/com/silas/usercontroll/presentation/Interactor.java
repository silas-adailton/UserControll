package br.com.silas.usercontroll.presentation;

import java.util.List;

import javax.inject.Inject;

import br.com.silas.usercontroll.InteractorExecutor;
import br.com.silas.usercontroll.Usuario;
import br.com.silas.usercontroll.data.Repository;
import br.com.silas.usercontroll.data.RepositoryExecutor;

public class Interactor {
    Repository mRepository;

    @Inject
    public Interactor(Repository mRepository) {
        this.mRepository = mRepository;
    }

    public void execute(Request request, final InteractorExecutor interactorExecutor) {
        mRepository.save(request.getUsuario(), new RepositoryExecutor() {
            @Override
            public void onSuccess(Object object) {
                interactorExecutor.onSuccess(object);
            }

            @Override
            public void onList(List<Usuario> list) {
                interactorExecutor.onList(list);
            }

            @Override
            public void onError(Throwable e) {
                interactorExecutor.onError(e);
            }

            @Override
            public void onCompleted() {
                interactorExecutor.onCompleted();
            }
        });

    }

    public void list(final InteractorExecutor interactorExecutor) {
        mRepository.toList(new RepositoryExecutor() {
            @Override
            public void onSuccess(Object object) {
                interactorExecutor.onSuccess(object);
            }

            @Override
            public void onList(List<Usuario> list) {
                interactorExecutor.onSuccess(list);
            }

            @Override
            public void onError(Throwable e) {
                interactorExecutor.onError(e);

            }

            @Override
            public void onCompleted() {
                interactorExecutor.onCompleted();

            }
        });

    }


    public static final class Request {
        private Usuario usuario;

        public Request(Usuario usuario) {
            this.usuario = usuario;
        }

        public Usuario getUsuario() {
            return usuario;
        }
    }
}
