package com.demo_tab_layout.pulkit;

import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo_tab_layout.pulkit.fragments.AboutUsFragment;
import com.demo_tab_layout.pulkit.fragments.HomeFragment;
import com.demo_tab_layout.pulkit.fragments.Settingsfragment;
import com.demo_tab_layout.pulkit.fragments.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView home_fa_icon, settings_fa_font, user_fa_icon, about_fa_icon;
    private Typeface fontAwesomeFont;
    private Toolbar dashboard_toolbar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private RelativeLayout rel_home, rel_setting, rel_user, rel_about_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashboard_toolbar = (Toolbar) findViewById(R.id.dashboard_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        rel_home = (RelativeLayout) findViewById(R.id.rel_home);
        rel_setting = (RelativeLayout) findViewById(R.id.rel_setting);
        rel_user = (RelativeLayout) findViewById(R.id.rel_user);
        rel_about_us = (RelativeLayout) findViewById(R.id.rel_about_us);

        home_fa_icon = (TextView) findViewById(R.id.home_fa_icon);
        settings_fa_font = (TextView) findViewById(R.id.settings_fa_font);
        user_fa_icon = (TextView) findViewById(R.id.user_fa_icon);
        about_fa_icon = (TextView) findViewById(R.id.about_fa_icon);

        fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        home_fa_icon.setTypeface(fontAwesomeFont);
        settings_fa_font.setTypeface(fontAwesomeFont);
        user_fa_icon.setTypeface(fontAwesomeFont);
        about_fa_icon.setTypeface(fontAwesomeFont);

        setSupportActionBar(dashboard_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("home_tab", "2");
        homeFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, homeFragment).commit();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, dashboard_toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, dashboard_toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        rel_home.setOnClickListener(this);
        rel_setting.setOnClickListener(this);
        rel_user.setOnClickListener(this);
        rel_about_us.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.rel_home) {

            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("home_tab", "2");
            homeFragment.setArguments(bundle);

            drawerLayout.closeDrawers();
            getSupportFragmentManager().beginTransaction().add(R.id.dashboard_container, homeFragment).commit();


        } else if (view.getId() == R.id.rel_setting) {
            drawerLayout.closeDrawers();
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new Settingsfragment()).commit();
        } else if (view.getId() == R.id.rel_user) {
            drawerLayout.closeDrawers();
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new UserFragment()).commit();
        } else {
            drawerLayout.closeDrawers();
            getSupportFragmentManager().beginTransaction().replace(R.id.dashboard_container, new AboutUsFragment()).commit();
        }

    }

    MenuItem action_settings;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        action_settings = menu.findItem(R.id.action_settings);

        action_settings.setVisible(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
