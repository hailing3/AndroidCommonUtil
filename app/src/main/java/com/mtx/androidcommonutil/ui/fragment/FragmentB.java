package com.mtx.androidcommonutil.ui.fragment;

import com.mtx.androidcommonutil.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentB extends Fragment {
    private TextView mTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, null);
        mTv = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTv.setText(getArguments().getString("data"));
    }
}
