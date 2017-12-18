package edu.upb.certi.annoy_o_larm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button configurations;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configurations = (Button) findViewById(R.id.menuConfigurationsButton);
        configurations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goConfigurations();
            }
        });
    }


    private void goConfigurations() {
        Intent intent = new Intent(this, ConfigurationsActivity.class);
        startActivity(intent);

        if(firebaseAuth.getCurrentUser()
                !=null){
            goLogIn();
        }
    }

    private void goLogIn() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
