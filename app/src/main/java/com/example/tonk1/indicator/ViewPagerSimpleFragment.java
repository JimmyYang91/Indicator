package com.example.tonk1.indicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Tonk1 on 2016/11/2.
 */

public class ViewPagerSimpleFragment extends Fragment {
    private String title;
    public static final String BUNDLE_TITLE = "title";

    public static ViewPagerSimpleFragment newInstance(String title){
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);

        ViewPagerSimpleFragment fragment = new ViewPagerSimpleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        title = bundle.getString(BUNDLE_TITLE);

        TextView tv = new TextView(getActivity());
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
