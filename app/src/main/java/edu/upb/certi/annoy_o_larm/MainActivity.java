package edu.upb.certi.annoy_o_larm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button configurations, alarms, contacts, perfil;



   private  FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() ==null){
            goLogIn();
        }

        configurations = (Button) findViewById(R.id.menuConfigurationsButton);
        configurations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goConfigurations();
            }
        });

        alarms = (Button) findViewById(R.id.menuAlarmsButton);
        alarms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goAlarms();
            }
        });

        contacts = (Button) findViewById(R.id.menuContactsButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goContacts();
            }
        });

        perfil = (Button) findViewById(R.id.menuProfileButton);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goProfile();
            }
        });
    }

    private void goProfile() {
        Intent intent = new Intent(this, InviteActivity.class);
        startActivity(intent);
    }

    private void goContacts() {
        Intent intent = new Intent(this, InviteActivity.class);
        startActivity(intent);


    }

    private void goAlarms() {
        Intent intent = new Intent(this, AlarmsActivity.class);
        startActivity(intent);

    }


    private void goConfigurations() {
        Intent intent = new Intent(this, ConfigurationsActivity.class);
        startActivity(intent);

    }

    private void goLogIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
