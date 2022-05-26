package com.frostyghost.music.language.quwitest.ui.chat;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.frostyghost.music.language.quwitest.models.ChannelsResponseModel;
import com.frostyghost.music.language.quwitest.models.LoginResponseModel;
import com.frostyghost.music.language.quwitest.network.QuwiApi;
import com.frostyghost.music.language.quwitest.network.QuwiNetService;

import java.util.Locale;

public class ChatViewModel extends ViewModel {

    private QuwiApi api = QuwiNetService.getInstance().getApi();

    public MutableLiveData<ChannelsResponseModel> channels = new MutableLiveData<>();
    public MutableLiveData<String> errorMsg = new MutableLiveData<>();

    public void getChannels(String token) {
        Log.d("QQ ", token);
        api.getChannels("Bearer " + token).observeForever(response -> {
            if (response.isSuccess() ) {
                if (response.getBody() != null){
                    channels.postValue(response.getBody());
                }
            }else {
                errorMsg.postValue(response.getErrorMessage());
            }
        });
    }

}
