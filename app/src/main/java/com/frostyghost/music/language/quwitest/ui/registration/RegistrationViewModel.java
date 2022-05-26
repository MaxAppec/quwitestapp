package com.frostyghost.music.language.quwitest.ui.registration;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frostyghost.music.language.quwitest.models.LoginModel;
import com.frostyghost.music.language.quwitest.models.LoginResponseModel;
import com.frostyghost.music.language.quwitest.models.RegistrationModel;
import com.frostyghost.music.language.quwitest.network.QuwiApi;
import com.frostyghost.music.language.quwitest.network.QuwiNetService;

public class RegistrationViewModel extends ViewModel {

    private QuwiApi api = QuwiNetService.getInstance().getApi();

    public MutableLiveData<Boolean> authStatus = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public void signup(RegistrationModel regModel) {
        api.signup(regModel).observeForever(response -> {
            if (response.isSuccess()) {
                if (response.getBody() != null){
                    authStatus.postValue(true);
                }else {
                    errorMsg.postValue("Ooops...");
                }
            }else {
                errorMsg.postValue(response.getErrorMessage());
            }
        });
    }

}
