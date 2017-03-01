package br.com.silas.usercontroll;


public class Usuario {

    private String mNome;
    private String mLogin;
    private String mSenha;
    private String urlFoto;

    public Usuario(String mNome, String mLogin, String mSenha, String urlFoto) {
        this.mNome = mNome;
        this.mLogin = mLogin;
        this.mSenha = mSenha;
        this.urlFoto = urlFoto;
    }

    public Usuario() {
    }

    public String getmNome() {
        return mNome;
    }

    public void setmNome(String mNome) {
        this.mNome = mNome;
    }

    public String getmLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }

    public String getmSenha() {
        return mSenha;
    }

    public void setmSenha(String mSenha) {
        this.mSenha = mSenha;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "Users: " + " Nome: " + this.mNome + " Login: " + this.mLogin + " Senha: " + this.mSenha;
    }
}

