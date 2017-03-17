package br.com.silas.usercontroll.data;


import java.util.List;

public interface RepositoryExecutor {

    void onSuccess(Object object);

    void onSuccess(List<Object> list);

    void onError(Throwable e);

    void onCompleted();
}
