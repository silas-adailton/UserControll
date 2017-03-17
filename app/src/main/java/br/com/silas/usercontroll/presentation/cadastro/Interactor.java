package br.com.silas.usercontroll.presentation.cadastro;

import java.util.List;

import javax.inject.Inject;

import br.com.silas.usercontroll.InteractorExecutor;
import br.com.silas.usercontroll.Usuario;
import br.com.silas.usercontroll.data.Repositorio;
import br.com.silas.usercontroll.data.Repository;
import br.com.silas.usercontroll.data.RepositoryExecutor;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Interactor {
    Repository mRepository;
    Repositorio mRepositorio;

    //@Inject
    public Interactor(Repository mRepository) {
        this.mRepository = mRepository;
    }

    @Inject
    public Interactor(Repositorio mRepositorio) {
        this.mRepositorio = mRepositorio;
    }

    public Observable executeSave(Usuario usuario) {

        return Observable.create(new ObservableOnSubscribe<Usuario>() {
            @Override
            public void subscribe(ObservableEmitter<Usuario> emitter) throws Exception {
                mRepositorio.saveUser(usuario).subscribeWith(new DisposableObserver<Usuario>() {
                    @Override
                    public void onNext(Usuario usuario) {
                        emitter.onNext(usuario);
                    }

                    @Override
                    public void onError(Throwable e) {
                        emitter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        emitter.onComplete();

                    }
                });
            }
        });
    }

    public void execute(Request request, final InteractorExecutor interactorExecutor) {
        mRepository.save(request.getUsuario(), new RepositoryExecutor() {
            @Override
            public void onSuccess(Object object) {
                interactorExecutor.onSuccess(object);
            }

            @Override
            public void onSuccess(List<Object> list) {

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

    public Observable listUser() {

        return mRepositorio.listUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

   /* public void list(final InteractorExecutor interactorExecutor) {
        mRepository.toList(new RepositoryExecutor() {
            @Override
            public void onSuccess(Object object) {
                interactorExecutor.onSuccess(object);
            }

            @Override
            public void onSuccess(List<Object> list) {
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

    }*/


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
