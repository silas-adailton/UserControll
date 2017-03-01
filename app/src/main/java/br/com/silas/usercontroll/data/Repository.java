package br.com.silas.usercontroll.data;

import java.util.List;


public interface Repository<T> {
    void save(T t, RepositoryExecutor repositoryExecutor);

    void toList(RepositoryExecutor repositoryExecutor);

    List<T> findAllByNome(T t);

    void update(T t);

    void delete(T t);
}
