package milkdelivery.mohit.com.mdapp.view.activity.profile;

import android.net.Uri;

import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileBody;

/**
 * Created by mohit on 13-03-2017.
 */

public interface ProfilePresenterInterface {
    void updateProfile(ProfileBody profileBody, Uri uri);
    void requestUserProfile(int userID);
}
