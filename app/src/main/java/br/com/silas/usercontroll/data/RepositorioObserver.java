package br.com.silas.usercontroll.data;

import br.com.silas.usercontroll.Usuario;
import io.reactivex.Observer;

public interface RepositorioObserver {

    Observer onNext(Usuario usuario);

    Observer onError(Throwable e);

    Observer onComplete();
}
