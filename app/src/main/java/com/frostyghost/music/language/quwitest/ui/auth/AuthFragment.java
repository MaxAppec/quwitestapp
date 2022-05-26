package com.frostyghost.music.language.quwitest.ui.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.frostyghost.music.language.quwitest.QuwiApp;
import com.frostyghost.music.language.quwitest.R;
import com.frostyghost.music.language.quwitest.core.NavigationFragment;
import com.frostyghost.music.language.quwitest.databinding.FragmentAuthBinding;
import com.frostyghost.music.language.quwitest.models.LoginModel;
import com.frostyghost.music.language.quwitest.utils.AbstractTextWatcher;
import com.frostyghost.music.language.quwitest.utils.ManagerSharedPreferences;

public class AuthFragment extends NavigationFragment<FragmentAuthBinding>{

    private AuthViewModel viewModel;

    @Override
    protected FragmentAuthBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentAuthBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupTextListeners();
        setupLoginBtn();
        setupObservers();
    }

    private void setupObservers() {
        viewModel.errorMsg.observe(getViewLifecycleOwner(), errorMsg ->{
            Toast.makeText(requireContext(), errorMsg + "", Toast.LENGTH_SHORT).show();
        });
        viewModel.authStatus.observe(getViewLifecycleOwner(), data ->{
            if (data.getToken() != null){
                ManagerSharedPreferences.getInstance(requireContext()).setAccessToken(data.getToken());
                navigate(R.id.chatListFragment);
            }
        });
    }

    private void setupViewModel() {
        viewModel = new AuthViewModel();
    }

    private void setupLoginBtn() {
        binding.bntLogin.setOnClickListener(view -> {
            if (isDataValid()){
                viewModel.login(new LoginModel(
                        binding.etPassword.getText().toString(),
                        binding.etEmail.getText().toString()));
            }
        });
    }

    private void setupTextListeners(){
        binding.etPassword.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                showLoginBtn();
            }
        });
        binding.etEmail.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                showLoginBtn();
            }
        });
    }

    private void showLoginBtn(){
        if (binding.etEmail.getText().toString().equals("")
                || binding.etPassword.getText().toString().equals("")){
            binding.bntLogin.setVisibility(View.GONE);
        }else {
            binding.bntLogin.setVisibility(View.VISIBLE);
        }
    }

    private Boolean isDataValid(){
        return validateEmail() && validatePass();
    }

    private boolean validatePass(){
        if (binding.etPassword.getText().toString().equals("")
                || binding.etPassword.getText().toString().length() <= 3 ){
            Toast.makeText(requireContext(), "Your password is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    };

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean validateEmail(){
        if (binding.etEmail.getText().toString().equals("")
                || !isValidEmail(binding.etEmail.getText().toString())){
            Toast.makeText(requireContext(), "Pls put your email", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
