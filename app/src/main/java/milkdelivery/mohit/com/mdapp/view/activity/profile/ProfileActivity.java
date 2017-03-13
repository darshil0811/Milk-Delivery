package milkdelivery.mohit.com.mdapp.view.activity.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;
import milkdelivery.mohit.com.mdapp.view.activity.BaseActivity;

public class ProfileActivity extends BaseActivity implements ProfileView {

    @BindView(R.id.textViewCity)
    TextView textViewCity;

    @BindView(R.id.textViewState)
    TextView textViewState;
    //Data Variables

    String[] ARRAY_CITY;
    String[] ARRAY_STATES;
    ProfilePresenterInterface profilePresenterInterface;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePresenterInterface=new ProfilePresenter(this,this);
        ButterKnife.bind(this);
        profilePresenterInterface.loadCities();
        profilePresenterInterface.loadStates();

    }

    @OnClick(R.id.textViewCity)
    public  void onCityClick()
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setItems(ARRAY_CITY, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textViewCity.setText(ARRAY_CITY[which]);

            }
        });
        dialog.show();
    }

    @OnClick(R.id.textViewState)
    public  void onStateClick()
    {
       AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setItems(ARRAY_STATES, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textViewState.setText(ARRAY_STATES[which]);

            }
        });
        dialog.show();
    }

    @Override
    public void loadProfile(ProfileResult profileResult) {

    }

    @Override
    public void updateProfileResult() {

    }

    @Override
    public void startProgress() {
        startProgressDialog(getString(R.string.loadingpleasewait));

    }

    @Override
    public void stopProgress() {
        stopProgressDialog();

    }

    @Override
    public void loadCities(String[] cities) {
        ARRAY_CITY=cities;

    }

    @Override
    public void loadStates(String[] states) {
        ARRAY_STATES=states;

    }
}
