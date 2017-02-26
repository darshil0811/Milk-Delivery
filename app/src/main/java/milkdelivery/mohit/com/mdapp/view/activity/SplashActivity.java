package milkdelivery.mohit.com.mdapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.ApplicationDialogs;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        dialogshow();
    }




    private void dialogshow()
    {
        ApplicationDialogs dialogs=new ApplicationDialogs();
        dialogs.showMessageDialog(this,getString(R.string.internetconnectionmessage));


    }
}
