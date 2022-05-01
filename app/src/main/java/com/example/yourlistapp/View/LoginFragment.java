package com.example.yourlistapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import com.example.yourlistapp.R;
import com.example.yourlistapp.ViewModel.LoginViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private Button loginButton;
    private EditText loginEmail;
    private EditText loginPassword;
    private ProgressBar progressBar;
    private Button register;
    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);

        navController = Navigation.findNavController(view);

        //setup the observer
        loginViewModel.getUserMutableLiveData().observe(getViewLifecycleOwner(), new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser != null){
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_loginFragment_to_randomFragment);
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstance){
        View view = layoutInflater.inflate(R.layout.fragment_login, container, false);

        loginEmail = view.findViewById(R.id.loginemail);
        loginPassword = view.findViewById(R.id.loginpassword);
        loginButton = view.findViewById(R.id.loginbutton);
        register = view.findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if(email.length() > 0 && password.length() > 0){
                    loginViewModel.register(email, password);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if(email.length() > 0 && password.length() > 0){
                    loginViewModel.login(email, password);
                }
            }
        });

        return view;
    }
}
