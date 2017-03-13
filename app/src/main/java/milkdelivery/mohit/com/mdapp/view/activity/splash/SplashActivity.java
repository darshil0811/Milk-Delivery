package milkdelivery.mohit.com.mdapp.view.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.ApplicationDialogs;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.connectionutils.ConnectionUtils;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import milkdelivery.mohit.com.mdapp.view.activity.home.HomeActivity;
import milkdelivery.mohit.com.mdapp.view.activity.login.LoginActivity;
import milkdelivery.mohit.com.mdapp.view.activity.profile.ProfileActivity;

public class SplashActivity extends AppCompatActivity {

    int Splash_Time = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkconnection();
    }

    private void checkconnection() {

        //show dialog if there is no internet connectivity

        ConnectionUtils connectionUtils = new ConnectionUtils();
        boolean value = connectionUtils.checkInternetConnection(this);
        if (value == true) {

            startSplash();
        } else {

            ApplicationDialogs dialog = new ApplicationDialogs();
            dialog.showMessageDialogWithFinish(this, getString(R.string.internetconnectionmessage));


        }


    }

    private void startSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Navigate to login or homescreen

                MW_SharedPref sharedPref = new MW_SharedPref();
                if (sharedPref.getInt(SplashActivity.this, sharedPref.USER_ID) > 0) {

                    startActivity(new Intent(SplashActivity.this, ProfileActivity.class));
                    finish();
                }
                else
                {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }

            }
        }, Splash_Time);

    }


}

