package br.com.silas.usercontroll.presentation.cadastro;

import java.util.List;

import br.com.silas.usercontroll.Usuario;

public interface Contract {
    interface View {
        void showListUser(List<Usuario> list);

        void showErrorFieldEmpity();

        void showProgress();

        void hideProgress();

        void setPresenter(Presenter presenter);

        void openCadastroActivity();

        void showCadastroSuccess();

        void showErrorCadastro(Throwable e);
    }

    interface Presenter {
        void saveUser(String nome, String login, String password);

        void ListUser();
    }
}
