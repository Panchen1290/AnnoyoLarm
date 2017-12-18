package edu.upb.certi.annoy_o_larm.adapters;

/**
 * Created by Andrew on 18-Dec-17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.upb.certi.annoy_o_larm.R;
import edu.upb.certi.annoy_o_larm.models.Contact;
import edu.upb.certi.annoy_o_larm.models.OnContactClickListener;

public class InviteAdapter extends RecyclerView.Adapter<InviteAdapter.ViewHolder> {
    private ArrayList<Contact> data;
    private Context context;

    private OnContactClickListener onContactClickListener;

    public InviteAdapter(Context context) {
        data = new ArrayList<Contact>();
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Contact c = data.get(position);
        holder.nameTextView.setText(c.getName());
        holder.numberTextView.setText(c.getNumber());
        holder.contactLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onContactClickListener!=null){
                    onContactClickListener.onContactClick(c);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void colocarDatos(ArrayList<Contact> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addContact(Contact c) {
        data.add(c);
        notifyDataSetChanged();
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void setOnContactClickListener(OnContactClickListener onContactClickListener) {
        this.onContactClickListener = onContactClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout contactLinearLayout;
        TextView nameTextView;
        TextView numberTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            contactLinearLayout = itemView.findViewById(R.id.contactLinearLayout);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
        }
    }
}
