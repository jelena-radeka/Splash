package com.example.splashvezba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private static final int SELECT_PICTURE = 1;
    private static final String TAG = "PERMISSIONS";
    private String imagePath = null;
    private ImageView preview;

   // private DatabaseHelper databaseHelper;
    private SharedPreferences prefs;

    public static String GLUMAC_KEY = "GLUMAC_KEY";

    private AlertDialog dijalog;

    public static final String NOTIF_CHANNEL_ID = "notif_channel_007";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        setupToolbar();

        prefs = PreferenceManager.getDefaultSharedPreferences(this);


       // showGlumac();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
           // case R.id.add_glumac:
//                addGlumac();
//                refresh();
             //   break;
            case R.id.settings:
                Intent settings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings);
                break;
//            case R.id.about_dialog:
//                showDialog();
//                break;
        }

        return super.onOptionsItemSelected(item);
    }


//    private void refresh() {
//
//        RecyclerView recyclerView = findViewById(R.id.rvList);
//        if (recyclerView != null) {
//            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//            recyclerView.setLayoutManager(layoutManager);
//
//
//            MyAdapter adapter = new MyAdapter(getDatabaseHelper(), this);
//            recyclerView.setAdapter(adapter);
//
//        }
//    }
//
//
//    private void showGlumac() {
//
//        final RecyclerView recyclerView = this.findViewById(R.id.rvList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        MyAdapter adapter = new MyAdapter(getDatabaseHelper(), this);
//        recyclerView.setAdapter(adapter);
//
//    }

//    private void reset() {
//        imagePath = "";
//        preview = null;
//    }
//
//    private void addGlumac() {
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.add_layout);
//        dialog.setTitle("Unesite podatke");
//        dialog.setCanceledOnTouchOutside(false);
//
//        Button chooseBtn = dialog.findViewById(R.id.choose1);
//        chooseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                preview = dialog.findViewById(R.id.preview_image1);
//                selectPicture();
//            }
//        });
//
//        Button add = dialog.findViewById(R.id.add_glumac);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                EditText glumacPrezime = dialog.findViewById(R.id.glumac_prezime);
//                EditText glumacIme = dialog.findViewById(R.id.glumac_ime);
//                EditText glumacBiografija = dialog.findViewById(R.id.glumac_biografija);
//                EditText glumacDatum = dialog.findViewById(R.id.glumac_datum);
//                EditText glumacRating = dialog.findViewById(R.id.glumac_rating);
//
//                if (preview == null || imagePath == null) {
//                    Toast.makeText(MainActivity.this, "Slika mora biti izabrana", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                Glumac glumac = new Glumac();
//                glumac.setmIme(glumacIme.getText().toString());
//                glumac.setmPrezime(glumacPrezime.getText().toString());
//                glumac.setmBiografija(glumacBiografija.getText().toString());
//                glumac.setmDatum(glumacDatum.getText().toString());
//                glumac.setmRating(Float.parseFloat(glumacRating.getText().toString()));
//                glumac.setImage(imagePath);
//
//                try {
//                    getDatabaseHelper().getmGlumacDao().create(glumac);
//
//
//                    boolean toast = prefs.getBoolean(getString(R.string.toast_key), false);
//                    boolean notif = prefs.getBoolean(getString(R.string.notif_key), false);
//
//                    if (toast) {
//                        Toast.makeText(MainActivity.this, "Unet nov glumac", Toast.LENGTH_LONG).show();
//
//                    }
//
//                    if (notif) {
//                        showNotification("Unet nov glumac");
//
//                    }
//
//                    refresh();
//                    reset();
//                } catch (NumberFormatException e) {
//                    Toast.makeText(MainActivity.this, "Rating mora biti broj", Toast.LENGTH_SHORT).show();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                dialog.dismiss();
//
//
//            }
//
//        });
//
//        Button cancel = dialog.findViewById(R.id.cancel);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }

//    private void showDialog() {
//        if (dijalog == null) {
//            dijalog = new AboutDialog(MainActivity.this).prepareDialog();
//        } else {
//            if (dijalog.isShowing()) {
//                dijalog.dismiss();
//            }
//        }
//        dijalog.show();
//    }

    public void showNotification(String poruka) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, NOTIF_CHANNEL_ID);
        builder.setSmallIcon(android.R.drawable.ic_input_add);
        builder.setContentTitle("Glumci");
        builder.setContentText(poruka);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        builder.setLargeIcon(bitmap);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "Description of My Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(NOTIF_CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void setupToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }
}