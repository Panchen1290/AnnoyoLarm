package edu.upb.certi.annoy_o_larm;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordRepEditText;
    private Button registerButton;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordRepEditText = findViewById(R.id.passwordRepEditText);
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
        progressBar = findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void register() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String rPassword = passwordRepEditText.getText().toString();
        if(!email.equals("")&& !password.equals("") && !rPassword.equals("")) {
            if (password.equals(rPassword)) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    goToLogin();
                                } else {
                                    showErrorMessage(task.getException().toString());
                                }
                            }
                        });
            } else {
                showErrorMessage("Las contraseñas no coinciden");
            }
        }else{
            showErrorMessage("Llene todos los campos por favor");
        }
    }

    private void goToLogin() {
        finish();
    }

    private void showErrorMessage(String s) {
        Toast.makeText(getApplicationContext(), "Hay error: " + s, Toast.LENGTH_LONG).show();
    }
}
