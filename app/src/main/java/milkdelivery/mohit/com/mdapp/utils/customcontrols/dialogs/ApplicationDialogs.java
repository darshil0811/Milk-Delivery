package milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import milkdelivery.mohit.com.mdapp.R;

/**
 * Created by mohit on 26-02-2017.
 */

public class ApplicationDialogs {


    //show dialog box and will close on press of ok

    public void showMessageDialogWithFinish(final Activity activity,String message)
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(activity);
        alertdialog.setCancelable(false);
        alertdialog.setTitle(activity.getString(R.string.feedback));
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
        alertdialog.show();

    }

    //now create a app dialog, on clicking the OK button activity will still runs...

    public  void showMessageDialog(Activity activity,String message)
    {

        AlertDialog.Builder dialog=new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setTitle(activity.getString(R.string.feedback));
        dialog.setMessage(message);
        dialog.setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });


            dialog.show();


    }

}
