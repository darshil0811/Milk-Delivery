package milkdelivery.mohit.com.mdapp.view.activity.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import milkdelivery.mohit.com.mdapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_UNCHANGED| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

    }



}


