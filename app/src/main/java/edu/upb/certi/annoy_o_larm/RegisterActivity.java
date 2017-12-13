package edu.upb.certi.annoy_o_larm;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private EditText passwordRep;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        passwordRep = findViewById(R.id.passwordRepEditText);
        register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String mail = email.getText().toString();
            String pass = password.getText().toString();
            String passRep = passwordRep.getText().toString();
            if(pass.equals(passRep)){
                registroUsuario(mail,password);
                goToLogin();
            }else {
                Toast.makeText(getApplicationContext(), "Sus contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }

    private void registroUsuario(String mail, EditText password) {
        //TODO
    }

    private void goToLogin() {
        //TODO
    }
}
