package milkdelivery.mohit.com.mdapp.view.activity.register;


import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;

/**
 * Created by mohit on 07-03-2017.
 */

public interface RegisterView {

    void startProgress();
    void stopProgress();
    void showFeedbackMessage( String message);
    void onRegisterComplete(RegisterResponse registerResponse);
}
