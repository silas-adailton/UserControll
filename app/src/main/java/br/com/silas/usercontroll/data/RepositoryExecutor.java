package br.com.silas.usercontroll.data;


import java.util.List;

import br.com.silas.usercontroll.Usuario;

public interface RepositoryExecutor {

    void onSuccess(Object object);

    void onList(List<Usuario> list);

    void onError(Throwable e);

    void onCompleted();
}
