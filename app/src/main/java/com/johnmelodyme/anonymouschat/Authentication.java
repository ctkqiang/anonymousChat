package com.johnmelodyme.anonymouschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication extends AppCompatActivity {

    Button Login, register;
    EditText email;
    EditText password;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String the_email;
                String the_password;
                the_email = email.getText().toString().trim();
                the_password = password.getText().toString().trim();

                if(TextUtils.isEmpty(the_email)){
                    pleaseEnterEmail();
                }
                if (TextUtils.isEmpty(the_password)){
                    pleaseEnterPassword();
                }
                if (the_password.length() < 6){
                    passwordpleasebelonger();
                }
                else {
                    firebaseAuth.signInWithEmailAndPassword(the_email, the_password)
                            .addOnCompleteListener(Authentication.this,
                                    new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                               createUserSucess();
                                            } else {
                                                failed();
                                            }
                                        }
                                    });
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String the_email;
                String the_password;
                the_email = email.getText().toString().trim();
                the_password = password.getText().toString().trim();

                if(TextUtils.isEmpty(the_email)){
                    pleaseEnterEmail();
                }
                if (TextUtils.isEmpty(the_password)){
                    pleaseEnterPassword();
                }
                if (the_password.length() < 4){
                    passwordpleasebelonger();
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(the_email,the_password)
                            .addOnCompleteListener(Authentication.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                       createUserSucess();
                                    } else {
                                        failed();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void failed() {
        Toast.makeText(getApplicationContext(),
                "LOG-ON:UserWithEmail:failed",
                Toast.LENGTH_SHORT)
                .show();
    }

    private void createUserSucess() {
        Toast.makeText(getApplicationContext(),
                "LOG-ON:UserWithEmail:success",
                Toast.LENGTH_SHORT)
                .show();

        Intent toConvo;
        toConvo = new Intent(Authentication.this,
                CONVERSATIONPAGE.class);
        startActivity(toConvo);
    }

    private void passwordpleasebelonger() {
        Toast.makeText(getApplicationContext(),
                "Password Minimum 6 Letters",
                Toast.LENGTH_SHORT)
                .show();
    }

    private void pleaseEnterPassword() {
        Toast.makeText(getApplicationContext(),
                "Please Enter Password",
                Toast.LENGTH_SHORT)
                .show();
    }

    private void pleaseEnterEmail() {
        Toast.makeText(getApplicationContext(),
                "Please Enter Email",
                Toast.LENGTH_SHORT)
                .show();
    }
}
