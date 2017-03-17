package br.com.silas.usercontroll;

import io.reactivex.Observer;

public interface InteractorObserver {
    Observer onNext(Usuario usuario);

    Observer onError(Throwable e);

    Observer onComplete();
}
