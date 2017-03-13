package milkdelivery.mohit.com.mdapp.view.activity.profile;

import android.app.Activity;

import milkdelivery.mohit.com.mdapp.R;

/**
 * Created by mohit on 13-03-2017.
 */

public class ProfilePresenter implements ProfilePresenterInterface {

    Activity activity;
    ProfileView view;

    public ProfilePresenter(Activity activity, ProfileView view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void loadCities() {

        String[]cities=activity.getResources().getStringArray(R.array.cities);
        view.loadCities(cities);

    }

    @Override
    public void loadStates() {

        String[]states=activity.getResources().getStringArray(R.array.states);
        view.loadStates(states);

    }
}
