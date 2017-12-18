package edu.upb.certi.annoy_o_larm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upb.certi.annoy_o_larm.adapters.AlarmsAdapter;
import edu.upb.certi.annoy_o_larm.models.Alarm;

public class AlarmsActivity extends AppCompatActivity {

    private RecyclerView alarmsRecyclerView;
    private RecyclerView.Adapter alarmsAdapter;

    private List<Alarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);

        alarmsRecyclerView = (RecyclerView) findViewById(R.id.alarmsRecyclerView);
        alarmsRecyclerView.setHasFixedSize(true);
        alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        alarms = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Alarm alarm = new Alarm(i*100, "trululu", true);
            alarms.add(alarm);
        }

        alarmsAdapter = new AlarmsAdapter(alarms, this);

        alarmsRecyclerView.setAdapter(alarmsAdapter);
    }
}
