package milkdelivery.mohit.com.mdapp.view.activity.profile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import milkdelivery.mohit.com.mdapp.R;
import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;
import milkdelivery.mohit.com.mdapp.utils.customcontrols.dialogs.sharedpref.MW_SharedPref;
import milkdelivery.mohit.com.mdapp.view.activity.BaseActivity;
import milkdelivery.mohit.com.mdapp.web.connection.IBaseurl;

public class ProfileActivity extends BaseActivity implements ProfileView,IBaseurl {
    @BindView(R.id.editTextAddress)
    EditText editTextAddress;

    @BindView(R.id.editTextUserName)
    EditText editTextUserName;

    @BindView(R.id.editTextPhone)
    EditText editTextPhone;


    @BindView(R.id.imageViewUserImage)
    CircleImageView imageViewUserImage;

    Uri SELECTED_PIC_URI;

    ProfilePresenterInterface profilePresenter;

    Bitmap QR_CODE_BITMAP;
    //Data Variables



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
       /* profilePresenter = new ProfilePresenter(this, this);
        MW_SharedPref sharedPref = new MW_SharedPref();

        profilePresenter.requestUserProfile(sharedPref.getInt(this, sharedPref.USER_ID));
*/

    }

    @OnClick(R.id.imageViewUserImage)
    public void onUserImageClick() {
        selectImageOptionsDialog(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == BaseActivity.CAMERA_PERMISSION) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.pleaseenablecameraPermission), Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == BaseActivity.GALLERY_PERMISSION) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.pleaseenableReadExternalStoragePermission), Toast.LENGTH_SHORT).show();
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == BaseActivity.CAMERA_INTENT) {

            } else if (requestCode == BaseActivity.GALLERY_INTENT) {
                SELECTED_PIC_URI = data.getData();

                imageViewUserImage.setImageURI(SELECTED_PIC_URI);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    public void loadProfile(ProfileResult profileResult) {





    }

    @Override
    public void startProgress() {
        startProgressDialog(getString(R.string.Loadingpleasewait));

    }

    @Override
    public void stopProgress() {
        stopProgressDialog();


    }

    @Override
    public void onProfileUpdateComplete() {

    }

    @Override
    public void showFeedbackMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();


    }
}
