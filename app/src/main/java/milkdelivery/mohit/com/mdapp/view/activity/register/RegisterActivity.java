package milkdelivery.mohit.com.mdapp.view.activity.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import milkdelivery.mohit.com.mdapp.view.activity.BaseActivity;

public class RegisterActivity extends BaseActivity implements RegisterView{

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.editTextUserName)
    EditText editTextUserName;
    @BindView(R.id.editTextMobileNumber)
    EditText editTextMobileNumber;
    RegisterPresenterInterface registerPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       registerPresenterInterface=new RegisterPresenter(this,this);
        ButterKnife.bind(this);
    }


        @OnClick(R.id.buttonClickRegister)
        public  void onButtonRegisterClick()
        {
                RegisterBody registerBody=new RegisterBody();
                registerBody.setEmail(editTextEmail.getText().toString());
                registerBody.setPassword(editTextPassword.getText().toString());
                registerBody.setMobileNumber(editTextMobileNumber.getText().toString());
                registerBody.setUserName(editTextUserName.getText().toString());
                registerBody.setDeviceToken("abc");
                registerPresenterInterface.requestRegister(registerBody);



        }
    @Override
    public void startProgress() {

        startProgressDialog(getString(R.string.registrationproess));

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
    public void onRegisterComplete(RegisterResponse registerResponse) {

        Toast.makeText(this,getString(R.string.registersuccess),Toast.LENGTH_SHORT).show();

    }
}
