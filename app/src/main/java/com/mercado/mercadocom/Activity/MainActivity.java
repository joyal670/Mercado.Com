package com.mercado.mercadocom.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mercado.mercadocom.Fragments.About_Fragment;
import com.mercado.mercadocom.Fragments.Chat_Fragment;
import com.mercado.mercadocom.Fragments.Home2_Fragment;
import com.mercado.mercadocom.Fragments.Home3_Fragment;
import com.mercado.mercadocom.Fragments.Home_Fragment;
import com.mercado.mercadocom.Fragments.Logout_Fragment;
import com.mercado.mercadocom.Fragments.MedicalFragment;
import com.mercado.mercadocom.Fragments.MyOrdersTab_Fragment;
import com.mercado.mercadocom.Fragments.Mycart_Fragment;
import com.mercado.mercadocom.Fragments.Settings_Fragment;
import com.mercado.mercadocom.Fragments.UserFeedback_Fragment;
import com.mercado.mercadocom.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToogle;
    TextView headerEmail;
    ImageView headerImage;
    String currentFragment = "other";

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;

    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
//            getSupportActionBar().setTitle("");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorBlue));
            }

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading Home Contents...");

            mDrawerLayout = findViewById(R.id.drawerLayout);
            mDrawerToogle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
            mDrawerLayout.addDrawerListener(mDrawerToogle);
            mDrawerToogle.syncState();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            loadHomePage();

//            final Home_Fragment fragment = new Home_Fragment();
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
//            fragmentTransaction.commit();

//            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Home");
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
//                {
//                        HomeModel model = dataSnapshot.getValue(HomeModel.class);
//                        String temp =  model.getHomeStatus();
//                        if(temp.equals(status_1))
//                        {
//                            Toast.makeText(MainActivity.this, "one", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            Toast.makeText(MainActivity.this, "two", Toast.LENGTH_SHORT).show();
//                        }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });

            headerEmail = navigationView.getHeaderView(0).findViewById(R.id.header_email);

            sharedPreferences = getSharedPreferences("data", 0);
            final String a1 = sharedPreferences.getString("userid", "");

            try {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String em = sharedPreferences.getString("useremail", "");
                headerEmail.setText(em);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadHomePage()
    {
        progressDialog.show();
        final DatabaseReference mReference = FirebaseDatabase.getInstance().getReference("Home").child("Status");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = (String) dataSnapshot.getValue();

                if (value.equals("1"))
                {
                    progressDialog.dismiss();
                    final Home_Fragment fragment = new Home_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment, "Home");
                    fragmentTransaction.commit();

                } else if (value.equals("2")) {
                    progressDialog.dismiss();
                    final Home2_Fragment fragment = new Home2_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment, "Home2");
                    fragmentTransaction.commit();
                } else if (value.equals("3")) {
                    progressDialog.dismiss();
                    final Home3_Fragment fragment = new Home3_Fragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, fragment, "Home3");
                    fragmentTransaction.commit();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.home) {
            loadHomePage();
        } else if (id == R.id.myorders) {
            currentFragment = "other";
            MyOrdersTab_Fragment fragment= new MyOrdersTab_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout,fragment,"My Order");
            fragmentTransaction.commit();
        }
        else if (id == R.id.medicalorders) {
            currentFragment = "other";
            MedicalFragment fragment = new MedicalFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "My Medicals");
            fragmentTransaction.commit();
        }
        else if (id == R.id.mycart) {
            currentFragment = "other";
            Mycart_Fragment fragment = new Mycart_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "My Cart");
            fragmentTransaction.commit();
        } else if (id == R.id.settings) {
            currentFragment = "other";
            Settings_Fragment fragment = new Settings_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Settings");
            fragmentTransaction.commit();
        } else if (id == R.id.feedback) {
            currentFragment = "other";
            UserFeedback_Fragment fragment = new UserFeedback_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Feedback");
            fragmentTransaction.commit();
        } else if (id == R.id.about) {
            currentFragment = "other";
            About_Fragment fragment = new About_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "About");
            fragmentTransaction.commit();

        }
        else if (id == R.id.chat) {
            currentFragment = "other";
            Chat_Fragment fragment = new Chat_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Chat");
            fragmentTransaction.commit();

        }
        else if (id == R.id.logout) {
            currentFragment = "other";
            Logout_Fragment fragment = new Logout_Fragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment, "Logout");
            fragmentTransaction.commit();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (currentFragment != "home") {
                loadHomePage();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_account:
                Intent intent = new Intent(MainActivity.this, UserAccount_Activity.class);
                startActivity(intent);
                return true;
            case R.id.item_cart:
                Mycart_Fragment fragmentcart = new Mycart_Fragment();
                FragmentTransaction fragmentTransactioncart = getSupportFragmentManager().beginTransaction();
                fragmentTransactioncart.replace(R.id.frame_layout, fragmentcart, "My Cart");
                fragmentTransactioncart.commit();
                return true;
            case R.id.item_share:
                try {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");
                    String sAux = "Let me recommend you mercado-Your Shopping Partner\n\n";
                    sAux = sAux + "https://play.google.com/store/apps/details?id=com.mercado.mercadocom \n\n";
                    i.putExtra(Intent.EXTRA_TEXT, sAux);
                    startActivity(Intent.createChooser(i, "choose one"));
                } catch (Exception e) {
                    Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
                }
                return true;

            case R.id.item_rate:
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            case R.id.item_update:

                try{

                PackageManager manager = this.getPackageManager();
                PackageInfo info = null;
                try {
                    info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                final String currentVersion = String.valueOf(info.versionCode);

                    final ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Cheking For Updates...");

                    progressDialog.show();
                    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference("Home").child("version");
                    mReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            progressDialog.dismiss();
                            String value = (String) dataSnapshot.getValue();
                            if(value.equals(currentVersion))
                            {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Your App is Up-To-Date");
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.create().show();

                            }
                            else
                            {
                                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Update Found");
                                builder.setPositiveButton("Update App", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        final String appPackageName = getPackageName();
                                        try {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                        } catch (android.content.ActivityNotFoundException anfe) {
                                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                        }
                                        dialog.dismiss();
                                    }
                                });
                                builder.setNegativeButton("Ignore Update", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        dialog.dismiss();
                                    }
                                });
                                builder.create().show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
