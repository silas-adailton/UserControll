package br.com.silas.usercontroll.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import br.com.silas.usercontroll.R;
import br.com.silas.usercontroll.presentation.cadastro.CadastroActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextLogin)
    EditText textLogin;
    @BindView(R.id.editTextSenha)
    EditText textSenha;
    @BindView(R.id.textViewSigIn)
    TextView textViewSigIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnLogin)
    void logar() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

    @OnClick(R.id.textViewSigIn)
    void SigIn() {
        startActivity(new Intent(this, CadastroActivity.class));
    }

}
