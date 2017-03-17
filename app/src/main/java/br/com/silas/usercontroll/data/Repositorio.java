package br.com.silas.usercontroll.data;


import java.util.List;

import br.com.silas.usercontroll.Usuario;
import io.reactivex.Observable;

public interface Repositorio<T> {
    Observable saveUser(T t);

    Observable<List<Usuario>> listUser();
}
