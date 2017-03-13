package milkdelivery.mohit.com.mdapp.view.activity.profile;

import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;

/**
 * Created by mohit on 13-03-2017.
 */

public interface ProfileView {

    void loadProfile(ProfileResult profileResult);
    void updateProfileResult();
    void startProgress();
    void stopProgress();
    void loadCities(String[] cities);
    void loadStates(String[] states);



}
