package id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment.BerandaFragment;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment.DataFragment;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment.KontrolFragment;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment.MonitoringFragment;
import id.ac.undip.ce.student.muhammadrizqi.inkubator_bayi.fragment.SimulasiFragment;

public class MainActivity extends AppCompatActivity

        implements NavigationView.OnNavigationItemSelectedListener {
    public static int navItemIndex = 0;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private String [] activityTitles;

    private static final String TAG_BERANDA = "beranda";
    private static final String TAG_MONITORING = "monitoring";
    public static final String TAG_SIMULASI = "simulasi";
    private static final String TAG_KONTROL = "kontrol";
    private static final String TAG_DATA = "data";
    public static String CURRENT_TAG = TAG_BERANDA;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mHandler = new Handler();
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadBerandaFragment();

        if(savedInstanceState == null){
            navItemIndex = 0;
            CURRENT_TAG = TAG_BERANDA;
            loadBerandaFragment();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_beranda) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_BERANDA;
            // Handle the camera action
        } else if (id == R.id.nav_monitoring) {
            navItemIndex = 1;
            CURRENT_TAG = TAG_MONITORING;
        } else if (id == R.id.nav_simulasi) {
            navItemIndex = 2;
            CURRENT_TAG = TAG_SIMULASI;
        } else if (id == R.id.nav_kontrol) {
            navItemIndex = 3;
            CURRENT_TAG = TAG_KONTROL;
        } else if (id == R.id.nav_data) {
            navItemIndex = 4;
            CURRENT_TAG = TAG_DATA;
        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Logout");
            alertDialogBuilder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            return true;
        }

        if (item.isChecked()){
            item.setChecked(false);
        }else {
            item.setChecked(true);
        }
        item.setChecked(true);

        loadBerandaFragment();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadBerandaFragment(){
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);

        getSupportActionBar().setTitle(activityTitles[navItemIndex]);

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG)!=null){
            drawer.closeDrawers();

            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                Fragment fragment = getBerandaFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        if (mPendingRunnable != null){
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getBerandaFragment(){
        switch (navItemIndex){
            case 0:
                BerandaFragment berandaFragment = new BerandaFragment();
                berandaFragment.setArguments(getIntent().getExtras());
                return berandaFragment;
            case 1:
                MonitoringFragment monitoringFragment = new MonitoringFragment();
                return monitoringFragment;
            case 3:
                KontrolFragment kontrolFragment = new KontrolFragment();
                return kontrolFragment;
            case 2:
                SimulasiFragment simulasiFragment = new SimulasiFragment();
                return simulasiFragment;
            case 4:
                DataFragment dataFragment = new DataFragment();
                return dataFragment;
             default:
                 return new  BerandaFragment();

        }
    }
}
