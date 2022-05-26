package com.frostyghost.music.language.quwitest.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.frostyghost.music.language.quwitest.core.NavigationFragment;
import com.frostyghost.music.language.quwitest.databinding.FragmentChatsBinding;
import com.frostyghost.music.language.quwitest.ui.chat.adapter.ChatNavigator;
import com.frostyghost.music.language.quwitest.ui.chat.adapter.ChatsAdapter;
import com.frostyghost.music.language.quwitest.utils.ManagerSharedPreferences;

public class ChatListFragment extends NavigationFragment<FragmentChatsBinding>
        implements ChatNavigator {

    @Override
    protected FragmentChatsBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentChatsBinding.inflate(inflater, container, false);
    }

    private ChatsAdapter adapter;
    private ChatViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRV();
        setupViewModel();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.errorMsg.observe(getViewLifecycleOwner(), errorMsg ->{
            Toast.makeText(requireContext(), errorMsg + "", Toast.LENGTH_SHORT).show();
        });
        viewModel.channels.observe(getViewLifecycleOwner(), data -> {
            if (data != null){
                adapter.updateMessagesData(data.getChannels());
            }
        });
    }

    private void setupViewModel() {
        viewModel = new ChatViewModel();
        viewModel.getChannels(ManagerSharedPreferences.getInstance(requireContext()).getAccessToken());
    }

    private void setupRV(){
        adapter = new ChatsAdapter(this);
        binding.rvChatList.setAdapter(adapter);
    }

    @Override
    public void goBack() {  }

    @Override
    public void onChatClick() {

    }
}
