package milkdelivery.mohit.com.mdapp.view.activity.login;

import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;

/**
 * Created by mohit on 05-03-2017.
 */

public interface LoginView {

    //here we will be defining what we have to do in Login Activity class

    void startProgress();
    void stopProgress();
    void showFeedbackMessage( String message);
    void onLoginComplete(LoginResultPrp loginResult);
}
