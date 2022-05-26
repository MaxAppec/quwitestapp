 package com.frostyghost.music.language.quwitest.network;

 import androidx.lifecycle.LiveData;

 import com.frostyghost.music.language.quwitest.models.ChannelsResponseModel;
 import com.frostyghost.music.language.quwitest.models.LoginModel;
 import com.frostyghost.music.language.quwitest.models.LoginResponseModel;
 import com.frostyghost.music.language.quwitest.models.RegistrationModel;
 import com.frostyghost.music.language.quwitest.network.adapter.Response;


 import retrofit2.http.Body;
 import retrofit2.http.GET;
 import retrofit2.http.Header;
 import retrofit2.http.Headers;
 import retrofit2.http.POST;
 import retrofit2.http.Query;

 public interface QuwiApi {

     @POST("auth/login")
     LiveData<Response<LoginResponseModel>> login(
             @Body LoginModel loginModel
     );

     @POST("auth/signup")
     LiveData<Response<LoginResponseModel>> signup(
             @Body RegistrationModel loginModel
     );

     @Headers({ "Content-Type: application/json;charset=UTF-8"})
     @GET("chat-channels")
     LiveData<Response<ChannelsResponseModel>> getChannels(
             @Header("Authorization") String token
     );
 }
