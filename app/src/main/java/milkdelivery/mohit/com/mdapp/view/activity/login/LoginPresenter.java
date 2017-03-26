package milkdelivery.mohit.com.mdapp.view.activity.login;

import android.app.Activity;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import milkdelivery.mohit.com.mdapp.web.connection.IBaseurl;
import milkdelivery.mohit.com.mdapp.web.connection.WebInterface;
import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.web.connection.WebRequestHandler;
import milkdelivery.mohit.com.mdapp.web.handler.LoginHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohit on 05-03-2017.
 */

public class LoginPresenter implements LoginPresenterInterface,LoginHandler{

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
        WebRequestHandler webRequestHandler = new WebRequestHandler();
        webRequestHandler.requestLogin(email, password, this);


    }

    @Override
    public void loginSuccess(LoginResultPrp loginResultPrp) {
        loginview.stopProgress();
        if (loginResultPrp.getResult() != null) {
            if (loginResultPrp.getResult().getStatus() == 1) {
                //Save Value to shared preferences
                MW_SharedPref sharedPref=new MW_SharedPref();
                sharedPref.setInt(activity,sharedPref.USER_ID,loginResultPrp.getResult().getId());
                loginview.onLoginSuccess();}
            else {
                //Show user wrong message
                loginview.showFeedbackMessage(activity.getString(R.string.wrongusernamepassword));
            }
        } else {
            //Retruend value is null so cant define anything here
            loginview.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));
        }

            }

    @Override
    public void loginFail(String message) {
        loginview.stopProgress();

        if(message!=null)
        {
            loginview.showFeedbackMessage(message);
        }
        else
        {
            loginview.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));

        }

    }

    }







