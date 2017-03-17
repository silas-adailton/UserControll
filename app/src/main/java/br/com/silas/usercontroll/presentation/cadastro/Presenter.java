package br.com.silas.usercontroll.presentation.cadastro;

import java.util.List;

import javax.inject.Inject;

import br.com.silas.usercontroll.Usuario;
import io.reactivex.observers.DisposableObserver;

public class Presenter implements Contract.Presenter {
    private Contract.View mView;
    private Interactor mInteractor;

    @Inject
    public Presenter(Contract.View mView, Interactor mInteractor) {
        this.mView = mView;
        this.mInteractor = mInteractor;
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void saveUser(String nome, String login, String password) {
        Usuario usuario = new Usuario(nome, login, password, null);
        mView.showProgress();

        if (nome.isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        }
        if (login.isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        }
        if (password.isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        } else {
            mView.showProgress();
            mInteractor.executeSave(usuario).subscribeWith(new DisposableObserver<Usuario>() {
                @Override
                public void onNext(Usuario u) {
                    mView.hideProgress();
                    mView.showCadastroSuccess();
                }

                @Override
                public void onError(Throwable e) {
                    mView.hideProgress();
                    mView.showErrorCadastro(e);
                }

                @Override
                public void onComplete() {
                }
            });
        }
    }

    @Override
    public void ListUser() {
        mView.showProgress();
        mInteractor.listUser().subscribeWith(new DisposableObserver<List<Usuario>>() {
            @Override
            public void onNext(List<Usuario> list) {
                mView.hideProgress();
                mView.showListUser(list);

            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.showErrorCadastro(e);

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /* mInteractor.execute(new Interactor.Request(usuario), new InteractorExecutor() {
                @Override
                public void onSuccess(Object object) {
                    mView.hideProgress();
                    mView.showCadastroSuccess();
                }

                @Override
                public void onSuccess(List<Object> list) {
                }

                @Override
                public void onError(Throwable e) {
                    mView.showErrorCadastro(e);
                }

                @Override
                public void onCompleted() {
                }
            });*/

    /*@Override
    public void ListUser() {
        mView.showProgress();
        mInteractor.list(new InteractorExecutor() {

            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onSuccess(List<Object> list) {
                mView.hideProgress();
                mView.showListUser(list);
            }

            @Override
            public void onError(Throwable e) {
                mView.hideProgress();
                mView.showErrorCadastro(e);
            }

            @Override
            public void onCompleted() {
            }

        });

    }*/
}
