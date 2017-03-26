package milkdelivery.mohit.com.mdapp.web.handler;

import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;

/**
 * Created by mohit on 26-03-2017.
 */

public interface RegisterHandler {
    void onRegistrationComplete(RegisterResponse registerResponse);
    void onRegistrationFail(String message);

}
