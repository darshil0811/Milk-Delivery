package milkdelivery.mohit.com.mdapp.view.activity.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.ApplicationDialogs;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.connectionutils.ConnectionUtils;
import milkdelivery.mohit.com.mdapp.view.activity.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    int Splash_Time=3000;

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

    private void checkconnection()
{

    //show dialog if there is no internet connectivity

    ConnectionUtils connectionUtils=new ConnectionUtils();
    boolean value=connectionUtils.checkInternetConnection(this);
    if (value==true)
    {

        startSplash();
    }

    else {

        ApplicationDialogs dialog=new ApplicationDialogs();
        dialog.showMessageDialogWithFinish(this,getString(R.string.internetconnectionmessage));




    }




}

    private void startSplash() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                // Navigate to login or homescreen

                startActivity(new Intent(SplashActivity.this,LoginActivity.class));

            }
        },Splash_Time);

    }
}
