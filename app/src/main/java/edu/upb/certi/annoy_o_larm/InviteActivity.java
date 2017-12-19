package edu.upb.certi.annoy_o_larm;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import edu.upb.certi.annoy_o_larm.adapters.InviteAdapter;
import edu.upb.certi.annoy_o_larm.models.Contact;
import edu.upb.certi.annoy_o_larm.models.OnContactClickListener;

public class InviteActivity extends AppCompatActivity {

    private static final String LOG = InviteActivity.class.getSimpleName();
    private Context context;

    private RecyclerView inviteRecyclerView;
    private InviteAdapter inviteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        context = this;

        inviteRecyclerView = findViewById(R.id.inviteRecyclerView);
        inviteRecyclerView.setHasFixedSize(true);
        inviteRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        inviteAdapter = new InviteAdapter(this);
        inviteRecyclerView.setAdapter(inviteAdapter);
        inviteAdapter.setOnContactClickListener(new OnContactClickListener() {
            @Override
            public void onContactClick(Contact contact) {
                Toast.makeText(context,contact.getName()+" "+contact.getNumber(),Toast.LENGTH_SHORT).show();
                String mensaje = "Despiertame de esta pesadilla por favor!!!!!";

                Uri uri = Uri.parse("smsto:"+contact.getNumber());
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", mensaje);
                startActivity(it);

                sendSMS(contact.getNumber(),mensaje);
            }
        });

        getContacts();
    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    public void getContacts() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        inviteAdapter.clear();

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        //Log.i(LOG, "Name: " + name);
                        //Log.i(LOG, "Phone Number: " + phoneNo);
                        inviteAdapter.addContact(new Contact(name, phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
    }
}
