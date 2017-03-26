package milkdelivery.mohit.com.mdapp.view.activity.register;

import android.app.Activity;


import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import milkdelivery.mohit.com.mdapp.web.connection.WebRequestHandler;
import milkdelivery.mohit.com.mdapp.web.handler.RegisterHandler;

/**
 * Created by Mobile on 3/6/17.
 */

public class RegisterPresenter implements RegisterPresenterInterface,RegisterHandler {

    Activity activity;
    RegisterView registerView;

    public RegisterPresenter(Activity activity,RegisterView registerView) {
        this.activity = activity;
        this.registerView=registerView;
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
        else if(!isFieldEmpty(registerBody.getMobileNumber()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.mobilenumberempty));
        }
        else if(!isFieldEmpty(registerBody.getPassword()))
        {
            registerView.showFeedbackMessage(activity.getString(R.string.passwordempty));
        }
        else
        {
            makeRegisterRequest(registerBody);
        }

    }


    private boolean isFieldEmpty(String value)
    {
        if(value.trim().length()==0)
        {
            return false;
        }
        return  true;
    }


    private void makeRegisterRequest(final RegisterBody registerBody)
    {
        registerView.startProgress();
        WebRequestHandler webRequestHandler=new WebRequestHandler();
        webRequestHandler.requestRegister(registerBody,this);

    }


    @Override
    public void onRegistrationComplete(RegisterResponse registerResponse) {
        registerView.stopProgress();

        if(registerResponse!=null)
        {
            if(registerResponse.getResult().getStatus()==1)
            {
                registerView.onRegistrationComplete();
            }
            else
            {
                registerView.showFeedbackMessage(activity.getString(R.string.emailexists));
            }
        }
        else
        {
            registerView.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));

        }

    }

    @Override
    public void onRegistrationFail(String message) {

    }
}
