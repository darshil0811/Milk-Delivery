package milkdelivery.mohit.com.mdapp.view.activity.profile;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileBody;
import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileUpdateResult;
import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;
import milkdelivery.mohit.com.mdapp.web.connection.WebRequestHandler;
import milkdelivery.mohit.com.mdapp.web.handler.ProfileReadHandler;
import milkdelivery.mohit.com.mdapp.web.handler.ProfileUpdateHandler;

/**
 * Created by mohit on 13-03-2017.
 */

public class ProfilePresenter implements ProfilePresenterInterface,ProfileReadHandler,ProfileUpdateHandler {

    Activity activity;
    ProfileView view;

    public ProfilePresenter(Activity activity, ProfileView view) {
        this.activity = activity;
        this.view = view;
    }



    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity.getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }


    @Override
    public void updateProfile(ProfileBody profileBody, Uri uri) {



        if(getRealPathFromURI(uri)!=null) {
            view.startProgress();
            File image=new File(getRealPathFromURI(uri));
            WebRequestHandler webRequestHandler=new WebRequestHandler();
            webRequestHandler.requestUpdateProfile(image,profileBody,this);

        }


    }

    @Override
    public void requestUserProfile(int userID) {

        view.startProgress();
        WebRequestHandler webRequestHandler=new WebRequestHandler();
        webRequestHandler.requestUserProfile(userID+"",this);



    }

    @Override
    public void onProfileUpdate(ProfileUpdateResult profileUpdateResult) {

        view.stopProgress();
        if(profileUpdateResult!=null)
        {
            if(profileUpdateResult.getResult().getStatus()==1)
            {
                view.showFeedbackMessage(activity.getString(R.string.profileupdated));

            }
            else
            {
                view.showFeedbackMessage(activity.getString(R.string.unbaletoupdateyourprofile));

            }

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));
        }

    }

    @Override
    public void onProfileUpdateFail(String message) {

        view.stopProgress();
        if(message!=null)
        {
            view.showFeedbackMessage(message);

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));
        }
    }



    @Override
    public void onProfileLoadComplete(ProfileResult profileResult) {

        view.stopProgress();

        if(profileResult!=null)
        {
            if(profileResult.getResult().getStatus()==1)
            {
                view.loadProfile(profileResult);
            }
            else
            {
                view.showFeedbackMessage(activity.getString(R.string.unabletoreadyourprofile));
            }
        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));
        }

    }

    @Override
    public void onProfileLoadFail(String message) {
        view.stopProgress();
        if(message!=null)
        {
            view.showFeedbackMessage(message);

        }
        else
        {
            view.showFeedbackMessage(activity.getString(R.string.somethingwentwrong));
        }
    }


}
