package milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs;

/**
 * Created by Mobile on 3/16/17.
 */

public class Validations {

    public static  boolean isFieldEmpty(String value)
    {
        if(value.trim().length()==0)
        {
            return false;
        }
        return  true;
    }

}
