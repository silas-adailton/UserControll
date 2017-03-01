package br.com.silas.usercontroll.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.silas.usercontroll.InteractorExecutor;
import br.com.silas.usercontroll.Usuario;

public class Presenter implements Contract.Presenter {

    List<Usuario> lista = new ArrayList<>();
    List<Object> l = new ArrayList<>();
    private Contract.View mView;
    private Interactor mInteractor;

    @Inject
    public Presenter(Contract.View view, Interactor interactor) {
        this.mView = view;
        this.mInteractor = interactor;
    }

    @Inject
    public void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void saveUser(Usuario usuario) {

        if (usuario.getmNome().isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        }
        if (usuario.getmLogin().isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        }
        if (usuario.getmSenha().isEmpty()) {
            mView.showErrorFieldEmpity();
            return;
        } else {
            mView.showProgress();
            mInteractor.execute(new Interactor.Request(usuario), new InteractorExecutor() {
                @Override
                public void onSuccess(Object object) {
                    mView.hideProgress();
                    mView.showCadastroSuccess();
                }

                @Override
                public void onList(List<Usuario> list) {

                }

                @Override
                public void onError(Throwable e) {
                    mView.showErrorCadastro(e);
                }

                @Override
                public void onCompleted() {


                }
            });
        }


    }

    @Override
    public void ListUser() {
        mInteractor.list(new InteractorExecutor() {

            @Override
            public void onSuccess(Object object) {

            }

            @Override
            public void onList(List<Usuario> list) {
                mView.showListUser(list);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onCompleted() {

            }

        });


    }
}
