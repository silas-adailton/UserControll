package br.com.silas.usercontroll;

import java.util.List;

public interface InteractorExecutor {

    void onSuccess(Object object);

    void onSuccess(List<Object> list);

    void onError(Throwable e);

    void onCompleted();
}
