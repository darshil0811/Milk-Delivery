package milkdelivery.mohit.com.mdapp.view.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.view.activity.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginView {

    LoginPresenterInterface loginPresenter;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        loginPresenter=new LoginPresenter(this,this);
        ButterKnife.bind(this);
    }


            @OnClick(R.id.buttonClickLogin)
            public void onLoginButtonClick()
            {
                loginPresenter.requestLogin(editTextEmail.getText().toString(),editTextPassword.getText().toString());


            }

    @Override
    public void startProgress() {
        startProgressDialog(getString(R.string.loadingpleasewait));
    }

    @Override
    public void stopProgress() {
        stopProgressDialog();

    }

    @Override
    public void showFeedbackMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginComplete(LoginResultPrp loginResult) {

        Toast.makeText(this,loginResult.getResult().getMessage(),Toast.LENGTH_SHORT).show();

    }

}


