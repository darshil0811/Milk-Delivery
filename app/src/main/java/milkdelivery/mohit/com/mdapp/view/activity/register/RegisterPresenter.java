package milkdelivery.mohit.com.mdapp.view.activity.register;

import android.app.Activity;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.network_connection.IBaseurl;
import milkdelivery.mohit.com.mdapp.model.network_connection.WebInterface;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.attr.editorExtras;
import static android.R.attr.value;
import static milkdelivery.mohit.com.mdapp.R.string.email;

/**
 * Created by mohit on 07-03-2017.
 */

public class RegisterPresenter implements RegisterPresenterInterface,IBaseurl {

    Activity activity;
    RegisterView registerView;

    public RegisterPresenter(Activity activity, RegisterView registerView) {
        this.activity = activity;
        this.registerView = registerView;
    }

    @Override
    public void requestRegister(RegisterBody registerBody) {

    if(!isFieldEmpty(registerBody.getUserName()))
    {
        registerView.showFeedbackMessage(activity.getString(R.string.usernameempty));

    }
    else if(!isFieldEmpty(registerBody.getEmail()))
    {
        registerView.showFeedbackMessage(activity.getString(R.string.emailempty));


    }
else if(!isFieldEmpty(registerBody.getPassword()))
    {
        registerView.showFeedbackMessage(activity.getString(R.string.passwordempty));


    }

    else if(!isFieldEmpty(registerBody.getMobileNumber()))
    {


    registerView.showFeedbackMessage(activity.getString(R.string.mobilenumberempty));

    }
    else
    {
        if(isEmailValid(registerBody.getEmail()))
        {
        makeRegisterRequest(registerBody);
    }}}

    private void makeRegisterRequest(final RegisterBody registerBody) {

        registerView.startProgress();
        Retrofit retro=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Call<RegisterResponse> result=retro.create(WebInterface.class).requestRegister(registerBody.getUserName(),registerBody.getEmail(),registerBody.getPassword(),registerBody.getDeviceToken(),registerBody.getMobileNumber());
        result.enqueue(new Callback<RegisterResponse>() {


            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                registerView.stopProgress();

                    if(response.body().getResult().getStatus()>0)
                    {
                        registerView.onRegisterComplete(response.body());
                    }
                    else if(response.body().getResult().getStatus()==-2)
                    {
                        registerView.showFeedbackMessage(activity.getString(R.string.emailexists));

                    }


            }



            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                registerView.stopProgress();
                registerView.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));

            }
        });}

    private boolean isFieldEmpty(String value) {
    if(value.trim().length()==0)
    {

        return  false;
    }
    return true;
    }

    private boolean isEmailValid(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern) && email.length() > 0)
        {
            return true;
        }

        else {

            registerView.showFeedbackMessage(activity.getString(R.string.emailisnotvalid));
            return false;

        }


    }
}