package milkdelivery.mohit.com.mdapp.web.handler;


import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileUpdateResult;

/**
 * Created by Mobile on 3/16/17.
 */

public interface ProfileUpdateHandler {

    void onProfileUpdate(ProfileUpdateResult profileUpdateResult);
    void onProfileUpdateFail(String message);
}
