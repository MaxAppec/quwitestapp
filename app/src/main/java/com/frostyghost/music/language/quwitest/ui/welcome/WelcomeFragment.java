package com.frostyghost.music.language.quwitest.ui.welcome;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.frostyghost.music.language.quwitest.R;
import com.frostyghost.music.language.quwitest.core.NavigationFragment;
import com.frostyghost.music.language.quwitest.databinding.FragmentRegistrationBinding;
import com.frostyghost.music.language.quwitest.databinding.FragmentWelcomeBinding;
import com.frostyghost.music.language.quwitest.models.LoginModel;
import com.frostyghost.music.language.quwitest.ui.auth.AuthViewModel;
import com.frostyghost.music.language.quwitest.utils.AbstractTextWatcher;
import com.frostyghost.music.language.quwitest.utils.ManagerSharedPreferences;

public class WelcomeFragment extends NavigationFragment<FragmentWelcomeBinding>{

    @Override
    protected FragmentWelcomeBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentWelcomeBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupClickListeners();
    }

    private void setupClickListeners() {
        binding.btnLogin.setOnClickListener((v)->navigate(R.id.authFragment));
        binding.btnRegistration.setOnClickListener((v)->navigate(R.id.registrationFragment));
    }

}
