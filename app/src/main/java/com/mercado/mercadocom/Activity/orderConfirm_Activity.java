package com.mercado.mercadocom.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mercado.mercadocom.Database.MobUser;
import com.mercado.mercadocom.R;
import com.raizlabs.android.dbflow.sql.language.Delete;

public class orderConfirm_Activity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    TextView placed_order_id;
    Button placed_order_continuebtn;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        try {

            sharedPreferences = getSharedPreferences("data", 0);
            String a1 = sharedPreferences.getString("userid", "");

            placed_order_id = findViewById(R.id.placed_order_id);
            placed_order_continuebtn = findViewById(R.id.placed_order_continuebtn);
            placed_order_id.setText(a1);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.gruplogo)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gruplogo))
                    .setContentTitle("New Order")
                    .setContentText("Thank you for your Order")
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);

            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(path);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "Your_Channel_ID";
                NotificationChannel channel = new NotificationChannel(channelId, "Chanel Human redable Text", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
            notificationManager.notify(1, builder.build());

            placed_order_continuebtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.gruplogo)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gruplogo))
                            .setContentTitle("New Order")
                            .setContentText("We will contact you soon")
                            .setAutoCancel(true)
                            .setDefaults(NotificationCompat.DEFAULT_ALL);

                    Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    builder.setSound(path);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        String channelId = "Your_Channel_ID";
                        NotificationChannel channel = new NotificationChannel(channelId, "Chanel Human redable Text", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManager.createNotificationChannel(channel);
                        builder.setChannelId(channelId);
                    }
                    notificationManager.notify(1, builder.build());

                    Delete.table(MobUser.class);

                    Intent intent = new Intent(orderConfirm_Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                    .setSmallIcon(R.drawable.gruplogo)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.gruplogo))
                    .setContentTitle("New Order")
                    .setContentText("We will contact you soon")
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL);

            Uri path = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(path);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "Your_Channel_ID";
                NotificationChannel channel = new NotificationChannel(channelId, "Chanel Human redable Text", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
            notificationManager.notify(1, builder.build());

            Delete.table(MobUser.class);

            Intent intent = new Intent(orderConfirm_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
