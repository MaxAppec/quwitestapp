package com.frostyghost.music.language.quwitest.ui.auth;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frostyghost.music.language.quwitest.models.LoginModel;
import com.frostyghost.music.language.quwitest.models.LoginResponseModel;
import com.frostyghost.music.language.quwitest.network.QuwiApi;
import com.frostyghost.music.language.quwitest.network.QuwiNetService;
import com.frostyghost.music.language.quwitest.network.adapter.Response;

public class AuthViewModel extends ViewModel {

    private QuwiApi api = QuwiNetService.getInstance().getApi();

    public MutableLiveData<LoginResponseModel> authStatus = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public void login(LoginModel loginModel) {
        api.login(loginModel).observeForever(response -> {
            if (response.isSuccess()) {
                if (response.getBody() != null){
                    authStatus.postValue(response.getBody());
                }else {
                    errorMsg.postValue("Ooops...");
                }
            }else {
                errorMsg.postValue(response.getErrorMessage());
            }
        });
    }

}
