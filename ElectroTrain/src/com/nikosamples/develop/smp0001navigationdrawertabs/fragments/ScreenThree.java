package com.nikosamples.develop.smp0001navigationdrawertabs.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.kture.pi1311.electrotrain.R;

public class ScreenThree extends Fragment {

    public ScreenThree() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.screen_third, container,
                false);

        return rootView;
    }

}