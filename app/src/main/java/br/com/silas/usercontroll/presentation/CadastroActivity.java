package br.com.silas.usercontroll.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.silas.usercontroll.ItemAdapter;
import br.com.silas.usercontroll.MainApplication;
import br.com.silas.usercontroll.R;
import br.com.silas.usercontroll.Usuario;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity implements Contract.View {
    @BindView(R.id.editTextCadasatroNome)
    EditText editTextNome;
    @BindView(R.id.editTextCadasatroLogin)
    EditText editTextLogin;
    @BindView(R.id.editTextCadasatroSenha)
    EditText editTextSenha;
    @BindView(R.id.progressBarCadastro)
    ProgressBar progressCadastro;
    @BindView(R.id.recycler_cadastro)
    RecyclerView recyclerViewCadastro;

    ItemAdapter adapter;

    @Inject
    Presenter mPresenter;
    Contract.Presenter mContraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
        progressCadastro.setVisibility(View.GONE);

        initDagger();
    }

    void initDagger() {
        DaggerCadastroComponent.builder()
                .mainComponent(MainApplication.getsMainComponent())
                .repositoryComponent(MainApplication.getsRepository())
                .cadastroModule(new CadastroModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.btnCadastroSalvar)
    void SalvarContato() {
        Usuario usuario = new Usuario();
        usuario.setmNome(editTextNome.getText().toString());
        usuario.setmLogin(editTextLogin.getText().toString());
        usuario.setmSenha(editTextSenha.getText().toString());

        mContraPresenter.saveUser(usuario);

    }

    @OnClick(R.id.btn_listar)
    void listarUsuario() {

        mContraPresenter.ListUser();

    }


    @Override
    public void showListUser(List<Usuario> list) {

        if (list.size() != 0) {
            //progress.setVisibility(View.GONE);
            recyclerViewCadastro.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewCadastro.setItemAnimator(new DefaultItemAnimator());
            recyclerViewCadastro.setHasFixedSize(true);

            adapter = new ItemAdapter(CadastroActivity.this, list);

            recyclerViewCadastro.setAdapter(adapter);
        } else {
            Toast.makeText(this, "A lista ainda esta vazia", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void showErrorFieldEmpity() {
        editTextNome.setError(getString(R.string.CampoObrigatorio));
        editTextLogin.setError(getString(R.string.CampoObrigatorio));
        editTextSenha.setError(getString(R.string.CampoObrigatorio));

    }

    @Override
    public void showProgress() {
        progressCadastro.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressCadastro.setVisibility(View.GONE);

    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.mContraPresenter = presenter;
    }

    @Override
    public void openCadastroActivity() {

    }

    @Override
    public void showCadastroSuccess() {
        Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorCadastro(Throwable e) {
        Toast.makeText(this, "Erro ao realizar o cadastro " + e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
