package milkdelivery.mohit.com.mdapp.web.connection;

import milkdelivery.mohit.com.mdapp.model.profileupdate.ProfileUpdateResult;
import milkdelivery.mohit.com.mdapp.model.properties.login.LoginResultPrp;
import milkdelivery.mohit.com.mdapp.model.properties.login.profile.ProfileResult;
import milkdelivery.mohit.com.mdapp.model.properties.login.register.RegisterResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by mohit on 05-03-2017.
 */

public interface WebInterface {

        @FormUrlEncoded
        @POST("milkwala/ws/login.php")
    Call<LoginResultPrp> requestLogin(@Field("email")String email,@Field("password")String password);

    @FormUrlEncoded
    @POST("milkwala/ws/register.php")
    Call<RegisterResponse> requestRegister(@Field("userName")String userName,@Field("email")String email,
    @Field("password")String password,@Field("deviceToken")String deviceToken,@Field("mobileNumber")String mobileNumber);

    @GET("milkwala/ws/returnProfile.php?userId={userID}")
    Call<ProfileResult>requestProfile(@Query("userID") String userID);

    @Multipart
    @POST("milkwala/ws/updateProfile.php")
    Call<ProfileUpdateResult> updateprofile(@Part("userId") RequestBody userId, @Part("userName")
            RequestBody userName, @Part("phone") RequestBody phone, @Part MultipartBody.Part image, @Part("address")
            RequestBody address, @Part("qrCode") RequestBody qrCode);

}



