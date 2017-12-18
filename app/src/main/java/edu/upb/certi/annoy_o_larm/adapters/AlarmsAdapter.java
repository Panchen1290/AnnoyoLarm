package edu.upb.certi.annoy_o_larm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.upb.certi.annoy_o_larm.R;
import edu.upb.certi.annoy_o_larm.models.Alarm;

/**
 * Created by Andrew on 18-Dec-17.
 */

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmsAdapter.ViewHolder> {

    private List<Alarm> alarms;
    private Context context;

    public AlarmsAdapter(List<Alarm> alarms, Context context) {
        this.alarms = alarms;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);

        holder.time.setText(alarm.getTime());
        holder.description.setText(alarm.getDescription());
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView time;
        private TextView description;

        public ViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.alarmTimeTextView);
            description = (TextView) itemView.findViewById(R.id.alarmDescTextView);
        }
    }
}
