package com.frostyghost.music.language.quwitest.ui.registration;

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
import com.frostyghost.music.language.quwitest.databinding.FragmentAuthBinding;
import com.frostyghost.music.language.quwitest.databinding.FragmentRegistrationBinding;
import com.frostyghost.music.language.quwitest.models.LoginModel;
import com.frostyghost.music.language.quwitest.models.RegistrationModel;
import com.frostyghost.music.language.quwitest.ui.auth.AuthViewModel;
import com.frostyghost.music.language.quwitest.utils.AbstractTextWatcher;
import com.frostyghost.music.language.quwitest.utils.ManagerSharedPreferences;

public class RegistrationFragment extends NavigationFragment<FragmentRegistrationBinding>{

    private RegistrationViewModel viewModel;

    @Override
    protected FragmentRegistrationBinding bindLayout(LayoutInflater inflater, ViewGroup container) {
        return FragmentRegistrationBinding.inflate(inflater, container, false);
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
        viewModel.authStatus.observe(getViewLifecycleOwner(), isSuccessful ->{
            if (isSuccessful){
                navigate(R.id.authFragment);
            }
        });
    }

    private void setupViewModel() {
        viewModel = new RegistrationViewModel();
    }

    private void setupLoginBtn() {
        binding.bntCreateAccount.setOnClickListener(view -> {
            if (isDataValid()){
                viewModel.signup(new RegistrationModel(
                        binding.etPassword.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etName.getText().toString()));
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
        binding.etName.addTextChangedListener(new AbstractTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                showLoginBtn();
            }
        });
    }

    private void showLoginBtn(){
        if (binding.etEmail.getText().toString().equals("")
                || binding.etPassword.getText().toString().equals("")
                || binding.etName.getText().toString().equals("")){
            binding.bntCreateAccount.setVisibility(View.GONE);
        }else {
            binding.bntCreateAccount.setVisibility(View.VISIBLE);
        }
    }

    private Boolean isDataValid(){
        return validateEmail() && validatePass() && validateName();
    }

    private boolean validateName(){
        if (binding.etName.getText().toString().equals("")
                || binding.etPassword.getText().toString().length() <= 3 ){
            Toast.makeText(requireContext(), "Your name is too short", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
