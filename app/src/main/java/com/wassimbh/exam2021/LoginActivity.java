package com.wassimbh.exam2021;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.wassimbh.exam2021.utilities.SharedPreferenceHelper;

import androidx.fragment.app.FragmentActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends FragmentActivity {

    private final String joueurStr = "joueur";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(isLoggedIn()){
            redirect();
        }

        TextInputEditText userNameText = findViewById(R.id.user_text);
        Button connectButton = findViewById(R.id.connect_btn);
        CheckBox rememberCheckBox = findViewById(R.id.remember_checkbox);

        connectButton.setOnClickListener(v -> {
            String username = userNameText.getText().toString();
            if(username.length() < 2){
                Toast.makeText(this, "Doit être supérieur a 2", Toast.LENGTH_SHORT).show();
            }else {
                if(validUserName(username)){
                    SharedPreferenceHelper.insertString("username", username, getApplicationContext());
                    redirect();
                }
                else{
                    Toast.makeText(this, "Ne doit pas commencer par le mot "+joueurStr, Toast.LENGTH_SHORT).show();
                }
            }
        });
        rememberCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferenceHelper.insertBool("remember", isChecked, getApplicationContext());

        });
    }

    private Boolean validUserName(String username){
        return !username.startsWith(joueurStr);
    }

    private Boolean isLoggedIn(){
        boolean remember =SharedPreferenceHelper.getBool("remember", getApplicationContext(), false);
        String username  = SharedPreferenceHelper.getString("username", "", getApplicationContext());
        return remember && username.length() > 0;
    }
    private void redirect(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}