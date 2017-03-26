package milkdelivery.mohit.com.mdapp.web.handler;

import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;

/**
 * Created by mohit on 26-03-2017.
 */

public interface LoginHandler {

    void loginSuccess(LoginResultPrp loginResultPrp);
    void loginFail(String message);
}
