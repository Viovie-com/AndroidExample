package com.viovie.example.landingslide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.viovie.example.R;

public class MainFragment extends Fragment {
    protected static final String PARAM_RESOURCE_ID = "LandingSlide.MainFragment.ResourceId";
    protected static final String PARAM_COLOR = "LandingSlide.MainFragment.Color";

    public static MainFragment newInstance(@DrawableRes int resId, int color) {
        MainFragment fragment = new MainFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_RESOURCE_ID, resId);
        bundle.putInt(PARAM_COLOR, color);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.landing_slide_fragment_main, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.main_image);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int resId = bundle.getInt(PARAM_RESOURCE_ID, 0);
            int color = bundle.getInt(PARAM_COLOR, Color.BLUE);

            imageView.setImageResource(resId);
            imageView.setBackgroundColor(color);
        }

        return view;
    }
}
