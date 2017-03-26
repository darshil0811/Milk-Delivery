package milkdelivery.mohit.com.mdapp.web.connection;

import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterBody;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import milkdelivery.mohit.com.mdapp.web.handler.LoginHandler;
import milkdelivery.mohit.com.mdapp.web.handler.RegisterHandler;
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



}
