package milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.sharedpref;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by mohit on 09-03-2017.
 */

public class MW_SharedPref {

    //we have created two string variables, one for storing USER_ID & second for Storing our SharedPref Name.

    public String USER_ID="USER_ID";

    String PREF_NAME="MW_SharedPrefMohit";


//here we have used Activity in function parameters so as to initialise Shared pref via activity class
    public void setInt(Activity activity,String variable,int value)
    {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("PREF_NAME",0);//string name,mode {0=>private}
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(variable,value);  //key,value
        editor.commit();


    }

    public int getInt(Activity activity,String variable)
    {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("PREF_NAME",0);
        int value=sharedPreferences.getInt(variable,-1);
        return value;

    }

    public  void clearAll(Activity activity)
    {
        SharedPreferences sharedPreferences=activity.getSharedPreferences("PREF_NAME",0);
        sharedPreferences.edit().clear().commit();
    }


}
