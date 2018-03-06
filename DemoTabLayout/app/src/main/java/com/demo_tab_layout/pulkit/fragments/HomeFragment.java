package com.demo_tab_layout.pulkit.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo_tab_layout.pulkit.adapter.ViewPagerAdapter;
import com.demo_tab_layout.pulkit.R;

/**
 * Created by pulkit on 21/7/17.
 */
public class HomeFragment extends Fragment {

    private ViewPager home_tab_viewpager;
    private TabLayout home_tab_layout;
    private View view;
    private String tab_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        tab_value = getArguments().getString("home_tab");

        initial();
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        viewPagerAdapter.addFragment(new Settingsfragment(), "Settings");
        viewPagerAdapter.addFragment(new UserFragment(), "User");
        viewPager.setAdapter(viewPagerAdapter);
    }

    private void initial() {

        home_tab_layout = (TabLayout) view.findViewById(R.id.home_tab_layout);
        home_tab_viewpager = (ViewPager) view.findViewById(R.id.home_tab_viewpager);

        setupViewPager(home_tab_viewpager);
        if (home_tab_viewpager != null) {
            home_tab_viewpager.clearOnPageChangeListeners();
            setupViewPager(home_tab_viewpager);
        }

        home_tab_layout.setupWithViewPager(home_tab_viewpager);

        if (tab_value != null) {
            if (tab_value.equalsIgnoreCase("2")) {
                home_tab_viewpager.setCurrentItem(1);
            }
        }
        if (home_tab_viewpager.getCurrentItem() == 1) {
//            header_name.setText("Settings");
        } else {
//            header_name.setText("User");
        }
    }

}
