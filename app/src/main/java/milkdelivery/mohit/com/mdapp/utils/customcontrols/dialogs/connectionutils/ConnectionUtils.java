package milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.connectionutils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mohit on 26-02-2017.
 */

public class ConnectionUtils {

    //now we will check if we are connected to internet or not


    public boolean checkInternetConnection(Activity activity) {

        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        boolean isNetworkConnected=false;

        try
        {

        if(networkInfo.isConnected()==true)
        {
            isNetworkConnected=true;

        }
        else
        {
            isNetworkConnected=false;


        }}
        catch (NullPointerException e)

            {
                isNetworkConnected=false;


            }

        try {
            if(networkInfo.isConnectedOrConnecting()==true)
            {
                isNetworkConnected=true;


            }

        else
        {

            isNetworkConnected=false;
        }}

        catch (NullPointerException e)
        {

            isNetworkConnected=false;

        }

return isNetworkConnected;
    }







}
