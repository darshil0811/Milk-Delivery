package milkdelivery.mohit.com.mdapp.web.handler;


import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;

/**
 * Created by Mobile on 3/17/17.
 */

public interface ProfileReadHandler {


    void onProfileLoadComplete(ProfileResult profileResult);
    void onProfileLoadFail(String message);

}
