package milkdelivery.mohit.com.mdapp.web.connection;

import java.io.File;
import java.util.concurrent.TimeUnit;

import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileBody;
import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileUpdateResult;
import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import milkdelivery.mohit.com.mdapp.web.handler.LoginHandler;
import milkdelivery.mohit.com.mdapp.web.handler.ProfileReadHandler;
import milkdelivery.mohit.com.mdapp.web.handler.ProfileUpdateHandler;
import milkdelivery.mohit.com.mdapp.web.handler.RegisterHandler;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohit on 26-03-2017.
 */

public class WebRequestHandler implements IBaseurl {



    private Retrofit initRetrofit()
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;

    }

    public void requestLogin(String email, String password, final LoginHandler loginHandler) {
        final Call<LoginResultPrp> result = initRetrofit().create(WebInterface.class).requestLogin(email, password);
        result.enqueue(new Callback<LoginResultPrp>() {
            @Override
            public void onResponse(Call<LoginResultPrp> call, Response<LoginResultPrp> response) {
                loginHandler.loginSuccess(response.body());
            }

            @Override
            public void onFailure(Call<LoginResultPrp> call, Throwable t) {
                loginHandler.loginFail(t.getMessage());
            }
        });
    }

    public void requestRegister(RegisterBody registerBody, final RegisterHandler handler)
    {
        final Call<RegisterResponse> result=initRetrofit().create(WebInterface.class).requestRegister(registerBody.getUserName(),registerBody.getEmail(),registerBody.getPassword(),registerBody.getDeviceToken(),registerBody.getMobileNumber());
        result.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                handler.onRegistrationComplete(response.body());

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                handler.onRegistrationFail(t.getMessage());
            }
        });
    }


    public void requestUserProfile(String userId, final ProfileReadHandler handler)
    {

        Call<ProfileResult> webInterface=initRetrofit().create(WebInterface.class).requestProfile(userId);
        webInterface.enqueue(new Callback<ProfileResult>() {
            @Override
            public void onResponse(Call<ProfileResult> call, Response<ProfileResult> response) {
                handler.onProfileLoadComplete(response.body());
            }

            @Override
            public void onFailure(Call<ProfileResult> call, Throwable t) {
                handler.onProfileLoadFail(t.getMessage());
            }
        });

    }


    public void requestUpdateProfile(File image, ProfileBody profileBody, final ProfileUpdateHandler handler)
    {
        RequestBody requestBodyimage = RequestBody.create(MediaType.parse("multipart/form-data"), image);
        MultipartBody.Part   partimage = MultipartBody.Part.createFormData("image", image.getName(), requestBodyimage);
        RequestBody userid = RequestBody.create(MediaType.parse("multipart/form-data"), profileBody.getUserId()+"");
        RequestBody userName = RequestBody.create(MediaType.parse("multipart/form-data"), profileBody.getUserName());
        RequestBody phonenumber = RequestBody.create(MediaType.parse("multipart/form-data"), profileBody.getPhone());
        RequestBody useraddress = RequestBody.create(MediaType.parse("multipart/form-data"), profileBody.getAddress());
        RequestBody code = RequestBody.create(MediaType.parse("multipart/form-data"), profileBody.getQrCode());
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(new OkHttpClient.Builder().readTimeout(2, TimeUnit.MINUTES).connectTimeout(2, TimeUnit.MINUTES).build()).addConverterFactory(GsonConverterFactory.create()).build();
        WebInterface api = retrofit.create(WebInterface.class);
        Call<ProfileUpdateResult> call = api.updateprofile(userid,userName,phonenumber,partimage,useraddress,code);
        call.enqueue(new Callback<ProfileUpdateResult>() {
            @Override
            public void onResponse(Call<ProfileUpdateResult> call, retrofit2.Response<ProfileUpdateResult> response) {
                handler.onProfileUpdate(response.body());

            }

            @Override
            public void onFailure(Call<ProfileUpdateResult> call, Throwable t) {
                handler.onProfileUpdateFail(t.getMessage());
            }
        });
    }



}
