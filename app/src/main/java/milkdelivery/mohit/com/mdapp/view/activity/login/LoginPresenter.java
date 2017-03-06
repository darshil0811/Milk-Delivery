package milkdelivery.mohit.com.mdapp.view.activity.login;

import android.app.Activity;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.network_connection.IBaseurl;
import milkdelivery.mohit.com.mdapp.model.network_connection.WebInterface;
import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohit on 05-03-2017.
 */

public class LoginPresenter implements LoginPresenterInterface,IBaseurl {

    Activity activity;
    LoginView loginview;

    public LoginPresenter(Activity activity, LoginView loginview) {
        this.activity = activity;
        this.loginview = loginview;
    }

    @Override
    public void requestLogin(String email, String password) {
        //now we are validating our email or password fields

        if(checkFieldEmpty(email,password))
        {
            if(isEmailValid(email))
            {
            
                makeLoginRequest(email,password);

            }


        }

    }

    //validation methods are implemented below
    private boolean checkFieldEmpty(String email, String password) {
        if(email.trim().length()==0)
        {
            loginview.showFeedbackMessage(activity.getString(R.string.emailempty));
                return false;

        }
        else if(password.trim().length()==0)
        {
            loginview.showFeedbackMessage(activity.getString(R.string.passwordempty));
            return false;

        }
        else
        {
            return true;


        }}

    private boolean isEmailValid(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.matches(emailPattern) && email.length() > 0)
        {
            return true;
        }

        else {

            loginview.showFeedbackMessage(activity.getString(R.string.emailisnotvalid));
            return false;

        }


    }





        //here we pass the request to the server

    private void makeLoginRequest(String email, String password) {

        loginview.startProgress();
        Retrofit retro=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Call<LoginResultPrp> result=retro.create(WebInterface.class).requestLogin(email, password);
        result.enqueue(new Callback<LoginResultPrp>() {
            @Override
            public void onResponse(Call<LoginResultPrp> call, Response<LoginResultPrp> response) {
                loginview.stopProgress();
                loginview.onLoginComplete(response.body());
            }

            @Override
            public void onFailure(Call<LoginResultPrp> call, Throwable t) {
                loginview.stopProgress();
                loginview.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));

            }
        });

    }}






