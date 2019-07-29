package com.gelecegiyazanlar.ceptediyetisyen.module;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gelecegiyazanlar.ceptediyetisyen.R;
import com.gelecegiyazanlar.ceptediyetisyen.base.BaseBindingActivity;
import com.gelecegiyazanlar.ceptediyetisyen.databinding.ActivityMainBinding;
import com.gelecegiyazanlar.ceptediyetisyen.module.dietitian.DietitianFragment;
import com.gelecegiyazanlar.ceptediyetisyen.module.healtyRecipe.HealtyRecipeFragment;
import com.gelecegiyazanlar.ceptediyetisyen.utils.Utils;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    private Toolbar toolbar;
    public ActionBar actionBar;
    private NavigationView navigationView;

    @Override
    protected int attachView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolbar();
        initDrawerMenu();
    }

    @Override
    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    private void initDrawerMenu() {
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
                Utils.hideSoftKeyBoard(MainActivity.this);
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                displayFragment(menuItem.getItemId(), menuItem.getTitle().toString());
                drawer.closeDrawers();
                return true;
            }
        });
    }


    private void displayFragment(int id, String title) {
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

        Fragment fragment = null;
        Bundle bundle = new Bundle();

        if (id == R.id.nav_dietitian) {
            fragment = new DietitianFragment();
        } else if (id == R.id.nav_healty_recipe) {
            fragment = new HealtyRecipeFragment();
        }

        if (fragment != null) {
            fragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_content, fragment);
            fragmentTransaction.commit();
        }

    }


}
