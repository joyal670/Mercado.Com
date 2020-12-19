package com.mercado.mercadocom.Fragments;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mercado.mercadocom.Activity.MainActivity;
import com.mercado.mercadocom.Model.FeedbackModel;
import com.mercado.mercadocom.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFeedback_Fragment extends Fragment {

    EditText userfeedback;
    Button userfeedbackbtn;
    DatabaseReference databaseReference;
    SharedPreferences sharedPreferences;
    ListView userfeebackListview;
    List<FeedbackModel> feedbackModel;
    public ProgressDialog progressDialog;

    public UserFeedback_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_userfeedback, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Thank you for your feedback...");

        userfeedback = view.findViewById(R.id.userfeedback);
        userfeedbackbtn = view.findViewById(R.id.userfeedbackbtn);

        userfeedbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback();
            }
        });
        return view;
    }

    private void feedback()
    {
        try {
            String fedbck = userfeedback.getText().toString();
            if(fedbck.isEmpty())
            {
                Toast.makeText(getContext(), "Type Some Text", Toast.LENGTH_SHORT).show();
            }
            else
            {
                progressDialog.show();
                sharedPreferences = this.getActivity().getSharedPreferences("data", 0);
                final String a1 = sharedPreferences.getString("userid", "");
                final String em = sharedPreferences.getString("useremail", "");


                databaseReference = FirebaseDatabase.getInstance().getReference("user_feedback").child("users").child(a1);

                String pushkey = databaseReference.push().getKey();

                FeedbackModel model = new FeedbackModel(fedbck);
                databaseReference.child(pushkey).setValue(model);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
                        .setSmallIcon(R.drawable.gruplogo)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gruplogo))
                        .setContentTitle("Hey, "+ em)
                        .setContentText("Thank you for your feedback")
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL);

                Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(path);

                NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelId = "Your_Channel_ID";
                    NotificationChannel channel = new NotificationChannel(channelId, "Chanel Human redable Text", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                    builder.setChannelId(channelId);
                }
                notificationManager.notify(1, builder.build());

                progressDialog.dismiss();

                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
