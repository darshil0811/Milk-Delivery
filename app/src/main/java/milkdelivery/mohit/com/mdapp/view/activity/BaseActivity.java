package milkdelivery.mohit.com.mdapp.view.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by mohit on 05-03-2017.
 */

public class BaseActivity extends AppCompatActivity{

    // here we will make our progress dialogs and give it to login activity

    ProgressDialog progressDialog;

    public void startProgressDialog(String message)
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(message);
        progressDialog.setTitle("Feedback!");
        progressDialog.show();
    }

    public  void stopProgressDialog()
    {

        if(progressDialog!=null)
        {

            progressDialog.dismiss();

        }

    }


}
